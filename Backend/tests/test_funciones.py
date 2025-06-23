import sys
import os

sys.path.insert(0, os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))

from app.services.clasificaciones_service import clasificacion_jugadores, posicion_jugador_clasificacion
from app.services.detalles_jugador_service import estadisticas_jugador
from app.services.partidos_jugador import partidos_jugador


# Comprobar que la función estadisticas_jugador devuelva los campos esperados para un usuario existente en la plataforma
def test_estadisticas_jugador():

    nickname = "ZywOo"
    resultado = estadisticas_jugador(nickname)

    assert resultado is not None, "No se obtuvo respuesta"
    assert isinstance(resultado, dict), "El resultado no es un diccionario"

    campos_esperados = [
        "nickname", "player_id", "pais", "avatar", "region_csgo", "nivel_csgo", "elo_csgo",
        "region_cs2", "nivel_cs2", "elo_cs2", "steam_id_64", "fecha_creacion_cuenta"
    ]
    for campo in campos_esperados:
        assert campo in resultado, f"Falta el campo '{campo}'"

    assert resultado["nickname"].lower() == nickname.lower(), "Nickname devuelto no coincide"
    assert isinstance(resultado["elo_cs2"], int), "ELO de CS2 no es un número"
    print("Test positivo: estadisticas_jugador")


# Comprobar que se devuelva la lista de jugadores clasificados de forma correcto y cada jugador tenga los campos esperados
def test_clasificacion_jugadores():
    resultado = clasificacion_jugadores("EU", "cs2", limite=5)

    assert resultado is not None, "No se obtuvo resultado"
    assert isinstance(resultado, list), "El resultado no es una lista"
    assert len(resultado) > 0, "Lista vacía"

    for jugador in resultado:
        assert isinstance(jugador, dict), "Cada jugador debe ser un diccionario"
        for campo in ["posicion", "nickname", "faceit_elo", "player_id", "country"]:
            assert campo in jugador, f"Falta el campo '{campo}' en un jugador"

    print("Test positivo: clasificacion_jugadores")


# Comprobar que la funcion devuelve un entero valido para la posicion en el ranking del jugador
def test_posicion_jugador_clasificacion():

    player_id = "d1a1aa41-f4ea-4035-97f7-cd522733c6d9"
    resultado = posicion_jugador_clasificacion("cs2", "EU", player_id)

    assert resultado is not None, "No se devolvió posición"
    assert isinstance(resultado, int), "La posición no es un número"
    assert resultado > 0, "La posición debe ser positiva"

    print("Test positivo: posicion_jugador_clasificacion")


# Comprobar que la funcion partidos_jugador imprima de forma correcta la información sobre los partidos jugados
def test_partidos_jugador(capsys):

    player_id = "d1a1aa41-f4ea-4035-97f7-cd522733c6d9"
    partidos_jugador(player_id, "cs2", limite=2)
    capturado = capsys.readouterr()
    assert "1  :" in capturado.out or "2  :" in capturado.out, "No se imprimió la información esperada de los partidos"

    print("Test positivo: partidos_jugador")
