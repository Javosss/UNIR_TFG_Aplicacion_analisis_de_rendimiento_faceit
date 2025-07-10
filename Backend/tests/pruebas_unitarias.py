import sys
import os
import pytest
from datetime import datetime

sys.path.insert(0, os.path.abspath(os.path.join(os.path.dirname(__file__), '..'))) # paths para imports

from app.services.clasificaciones_service import clasificacion_jugadores
from app.services.detalles_jugador_service import estadisticas_jugador
from app.services.partidos_jugador import partidos_jugador, estadisticas_partido, devolver_ids_partidos_jugador
from app.services.equipos_jugador import extraer_equipos_jugador, detalles_del_equipo, obtener_equipos_completos
from app.services.estadisticas_jugador import extraer_resumen_estadisticas_jugador
import app.routes.faceit_routes



@pytest.fixture # Cliente para hacer las pruebas
def client():
    from app.routes.faceit_routes import app as flask_app  # Corregir la ruta de importación
    flask_app.config['TESTING'] = True
    with flask_app.test_client() as client:
        yield client


# Pruebas para detalles_jugador_service.py

def test_estadisticas_jugador_nickname_valido(): # Se prueba que la función devuelva datos correctos para un nickname existente
    nickname = "ZywOo"
    resultado = estadisticas_jugador(nickname)

    assert resultado is not None, "No se obtuvo respuesta" # Resultado que no sea None
    assert isinstance(resultado, dict), "El resultado debería ser un diccionario" # Devuelve un diccionario
    assert resultado["nickname"].lower() == nickname.lower(), "Nickname no coincide" # Se comprueba si coincide el nickname obtenido con el nickname en minúscula
    assert isinstance(resultado["elo_cs2"], int), "ELO debería ser un entero" # Debe devolver el elo como un entero
    assert "avatar" in resultado, "Falta campo avatar" # El campo avatar existe


def test_estadisticas_jugador_nickname_invalido(): # Se prueba el comportamiento para nicknames que no existen
    with pytest.raises(Exception): # Si se le pasa un nickbane que no es válido debe mostrar una excepción
        estadisticas_jugador("nickname_inexistente_12345")


def test_formato_fecha_creacion(): # Se prueba que el formato de la fecha de creación sea correcto (días/mes/año/)
    resultado = estadisticas_jugador("ZywOo")  # Otra vez un nickname que existe

    try:
        datetime.strptime(resultado["fecha_creacion_cuenta"], "%d/%m/%Y")
    except ValueError:
        pytest.fail("Formato de fecha incorrecto")



# Pruebas en clasificaciones_service.py


@pytest.mark.parametrize("region,juego,pais,limite", [
    ("EU", "cs2", None, 5),
    ("EU", "cs2", "fr", 10),
    ("NA", "cs2", "us", 3)
])


def test_clasificacion_jugadores_varios_parametros(region, juego, pais, limite): # Se prueba la función con varias combinaciones de parámetros
    resultado = clasificacion_jugadores(region, juego, pais=pais, limite=limite)

    assert isinstance(resultado, list), "Debería devolver una lista" # devuelve una lista
    assert len(resultado) <= limite, f"No debería devolver más de {limite} resultados"
    if len(resultado) > 0: # Cada jugador tiene el campo nickname y elo
        primer_jugador = resultado[0]
        assert "nickname" in primer_jugador, "Falta campo nickname"
        assert "faceit_elo" in primer_jugador, "Falta campo faceit_elo"



# Pruebas en partidos_jugador.py

def test_partidos_jugador_basico(): # Prueba básica para obtener los partidos, debe devolver una lista y contener el id y resiltado

    player_id = "d1a1aa41-f4ea-4035-97f7-cd522733c6d9"  # id válido
    partidos = partidos_jugador(player_id, "cs2", limite=5)

    assert isinstance(partidos, list), "Debería devolver una lista"

    if len(partidos) > 0:

        primer_partido = partidos[0]
        assert "ID del partido" in primer_partido, "Falta ID del partido"
        assert "Resultado del partido" in primer_partido, "Falta resultado"


def test_estadisticas_partido(): # Prueba para las estadísticas detalladas del partido
    # Campos que existen
    match_id = "1-0faa6008-b45d-4651-ace8-0b1cdb1e1697"
    player_id = "d1a1aa41-f4ea-4035-97f7-cd522733c6d9"
    stats = estadisticas_partido(match_id, player_id)

    assert isinstance(stats, dict), "Debería devolver un diccionario" # Devuelve un diccionario
    # Tiene los campos kills, muertes y adr
    assert "Kills" in stats, "Falta campo Kills"
    assert "Deaths" in stats, "Falta campo Deaths"
    assert "ADR" in stats, "Falta campo ADR"


def test_devolver_ids_partidos(): # Devuelve el historial de partidos en una lista con los ids (como se desarrolló en la funcion)

    player_id = "d1a1aa41-f4ea-4035-97f7-cd522733c6d9"
    ids = devolver_ids_partidos_jugador(player_id, "cs2", limite=10)

    assert isinstance(ids, list), "Debería devolver una lista" # Devuelve la lista de strings con todas las ids de los partidos
    # Se comprueba que devuelva 10 o menos (o menos porque podría ser que el jugador no haya jugado 10 partidos, pero lo importante es que no devuelva más de 10)
    assert len(ids) <= 10, "No debería devolver más de 10 IDs"
    assert all(isinstance(i, str) for i in ids), "Todos los IDs deberían ser strings" # Se comprueba que son todos strings


# Pruebas para equipos_jugador.py
def test_extraer_equipos_jugador(): # Devuelve los equipos asociados a un jugador

    player_id = "d682a62e-43f5-49be-b8c9-de923adcf564"
    equipos = extraer_equipos_jugador(player_id, limite=3)

    assert isinstance(equipos, list), "Debería devolver una lista" # Devuelve una lista

    assert all(isinstance(eid, str) for eid in equipos), "IDs de equipo deberían ser strings" # Se comprueba que todos son strings


def test_detalles_equipo(): # Detalles de un equipo asociado
    team_id = "108a5c12-2252-4309-a315-c3d63d735c83"  # Id de equipo válido
    detalles = detalles_del_equipo(team_id)

    assert isinstance(detalles, dict), "Debería devolver un diccionario" #  Devuelve un diccionario

    if "error" not in detalles:  # Solo verificar si no hay error
        assert "Nickname equipo" in detalles, "Falta nickname del equipo"



# Pruebas para estadisticas_jugador.py


def test_resumen_estadisticas(): # Resumen de estadísticas
    player_id = "d1a1aa41-f4ea-4035-97f7-cd522733c6d9"
    resumen = extraer_resumen_estadisticas_jugador(player_id, "cs2", num_partidos=5)

    assert isinstance(resumen, dict), "Debería devolver un diccionario" # Devuelve un diciconario
    assert "Partidos analizados" in resumen, "Falta campo Partidos analizados"
    assert "Estadísticas promedio por partido" in resumen, "Falta estadísticas promedio"



# Pruebas para los endpoints de Flask

def test_ruta_estadisticas_jugador(client): #  /api/player
    response = client.get('/api/player?nickname=ZywOo')

    assert response.status_code == 200, "Debería devolver status 200" # Devuelve un 200
    # Contiene el campo nickname en la respuesta
    data = response.get_json()
    assert "nickname" in data, "Falta campo nickname en la respuesta"


def test_ruta_clasificacion(client): #  /api/clasificacion
    response = client.get('/api/clasificacion?region=EU&juego=cs2&limite=5')
    assert response.status_code == 200, "Debería devolver status 200"
    # Devuelve una lista la respuesta
    data = response.get_json()
    assert isinstance(data, list), "Debería devolver una lista"


def test_ruta_equipos_jugador(client): # apu/equipos_jugador
    response = client.get('/api/equipos_jugador?player_id=d682a62e-43f5-49be-b8c9-de923adcf564')
    assert response.status_code == 200, "Debería devolver status 200"

    # Devuelve una lista
    data = response.get_json()
    assert isinstance(data, list), "Debería devolver una lista de equipos"

