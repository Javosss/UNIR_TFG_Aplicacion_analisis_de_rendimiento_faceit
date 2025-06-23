import app.utils.peticion_HTTP
from app.utils.peticion_HTTP import hacer_request
from datetime import datetime

# Función para extraer los 100 últimos partidos de un jugador junto con algunos detalles básicos del partido
# Esta extracción no devuelve datos detallados del partido, para ello hay otro endpoint de la API de FACEIT
def partidos_jugador(player_id, juego, desde=None, hasta=None, comienzo=None, limite=None):
    parametros = {"game" : juego, "from" : desde, "to" : hasta, "offset" : comienzo, "limit" : limite}
    cuerpo = hacer_request(f"players/{player_id}/history",parametros)

    lista_partidos = cuerpo["items"]
    partidos_devolver = [] # Lista para acumular todos los partidos a devolver

    for partido in lista_partidos:
        match_id = partido["match_id"]
        juego = partido["game_id"]
        region = partido["region"]
        modo = partido["game_mode"]
        nombre_competicion = partido["competition_name"]
        tipo_competicion = partido["competition_type"]

        # Horas de comienzo y finalizacion (se formatean con datetime)
        started_at = partido["started_at"]
        hora_comienzo = datetime.fromtimestamp(started_at).strftime("%d/%m/%Y %H:%M")
        finished_at = partido["finished_at"]
        hora_finalizacion = datetime.fromtimestamp(finished_at).strftime("%d/%m/%Y %H:%M")

        # Resultados y equipo que ha ganado el partido
        resultados_partido = partido["results"]
        resultado = resultados_partido["score"] # Diccionario con los resultados {'faction1': 1, 'faction2': 0}
        equipo_ganador = resultados_partido["winner"]

        # Lógica para determinar si el jugador está en el equipo ganador (El JSON que responde llama a los equipos faction1 o faction2)
        equipo_jugador = None
        for faction, equipo_data in partido["teams"].items():  # 'faction1' y 'faction2'
            for jugador in equipo_data["players"]:
                if jugador["player_id"] == player_id:
                    equipo_jugador = faction
                    break
            if equipo_jugador:
                break

        if equipo_jugador == equipo_ganador:
            resultado_jugador = "Ganada"
        else:
            resultado_jugador = "Perdida"

        partidos_devolver.append({
            "Nombre competicion" : nombre_competicion,
            "Tipo competicion" : tipo_competicion,
            "Region" : region,
            "Modo" : modo,
            "Juego" : juego,
            "ID del partido" : match_id,
            "Resultado del partido" : resultado_jugador,
            "Hora de comienzo": hora_comienzo,
            "Hora de finalizacion": hora_finalizacion,
        })
    return partidos_devolver

# Función para devolver únicamente las ids de las partidas.
# Se define esta función ya que otras rutas de la API de FACEIT ofrecen datos más detallados para partidas jugadas, pasando el id del partido como paramétro.
def devolver_ids_partidos_jugador(player_id, juego, desde=None, hasta=None, comienzo=None, limite=None):
    parametros = {"game": juego, "from": desde, "to": hasta, "offset": comienzo, "limit": limite}
    cuerpo = hacer_request(f"players/{player_id}/history", parametros)

    # Se extraen los ids de los partidos del jugador y se guardan en una lista
    ids_partidos = []
    for partido in cuerpo["items"]:
        match_id = partido["match_id"]
        ids_partidos.append(match_id)  # Añadir ID a la lista

    return ids_partidos



# Función para extraer estadísticas de un partido jugado
def estadisticas_partido(match_id, player_id=None):
    cuerpo = hacer_request(f"matches/{match_id}/stats")

    # Diccionario con todas las estadísticas necesarias (algunos nombres los dejo en inglés por conveniencia)
    datos = {
        'Mapa': None,
        'Resultado del partido': None,
        'ID del equipo ganardor': None,

        'Nickname del jugador': None,
        'jugador_equipo': None,

        'Utility Damage': None,
        'Flash Success Rate per Match': None,
        'Double_Kills': None,
        'First_Kills': None,
        '1v2Wins': None,
        'Sniper Kill Rate per Match': None,
        'Utility Successes': None,
        'Utility Success Rate per Match': None,
        'Headshots %': None,
        'Enemies Flashed per Round in a Match': None,
        'Pistol Kills': None,
        'K/D Ratio': None,
        'Utility Usage per Round': None,
        'Enemies Flashed': None,
        'Penta Kills': None,
        'Flash Count': None,
        'Entry Wins': None,
        'Clutch Kills': None,
        'K/R Ratio': None,
        'Utility Damage per Round in a Match': None,
        'Assists': None,
        '1v1Wins': None,
        'Match 1v2 Win Rate': None,
        'Damage': None,
        'Sniper Kills': None,
        'Utility Count': None,
        'Kills': None,
        'Deaths': None,
        'Entry Count': None,
        'Match Entry Success Rate': None,
        'Knife Kills': None,
        'Sniper Kill Rate per Round': None,
        'Utility Enemies': None,
        '1v1Count': None,
        '1v2Count': None,
        'Triple Kills': None,
        'MVPs': None,
        'Flash Successes': None,
        'Match 1v1 Win Rate': None,
        'ADR': None,
        'Headshots': None,
        'Match Entry Rate': None,
        'Result': None,
        'Utility Damage Success Rate per Match': None,
        'Zeus Kills': None,
        'Flashes per Round in a Match': None,
        'Quadro Kills': None
    }


    # Acceder a las estadísticas de la ronda
    rondas = cuerpo["rounds"]
    for ronda in rondas:
        estadisticas_rondas = ronda["round_stats"]

        mapa = estadisticas_rondas["Map"]
        resultado_numerico_rondas = estadisticas_rondas["Score"]
        id_equipo_ganador = estadisticas_rondas["Winner"] # ID para extraer más estadísticas de cada equipo

        datos.update(
            {'Mapa': mapa, "Resultado del partido": resultado_numerico_rondas}
        )

        # Estadísticas más detalladas de cada equipo de la partida
        equipos = ronda["teams"]
        for equipo in equipos:
            jugadores = equipo["players"]

            for jugador in jugadores:

                if jugador["player_id"] == player_id:
                    nickname = jugador["nickname"]
                    id_equipo_jugador = equipo["team_id"]

                    datos.update({"Nickname del jugador": nickname, "ID del equipo ": id_equipo_jugador})

                    # Extraer todas las estadísticas del jugador en cuestión
                    estadisticas = jugador["player_stats"]
                    # Con el bucle for se actualizan todas las estadísticas en el diccionario
                    for estadistica,valor in estadisticas.items():
                        clave = estadistica.replace(" ", "_") # Se convierte el diccionario en pares para poder iterarlos
                        if clave in datos:
                            datos[clave] = valor
                    return datos
            return datos


# Función para extraer detalles de un partido
def detalles_partido(match_id):
    cuerpo = hacer_request(f"matches/{match_id}")

    demo_url = cuerpo["demo_url"]
    faceit_url = cuerpo["faceit_url"]
    status = cuerpo["status"]

    resultados_detallados = cuerpo["detailed_results"]
    resultado = cuerpo["results"]
    equipos = cuerpo["teams"]


    print(cuerpo)






print(partidos_jugador("d1a1aa41-f4ea-4035-97f7-cd522733c6d9","cs2", limite=100))
#devolver_ids_partidos_jugador("d1a1aa41-f4ea-4035-97f7-cd522733c6d9","cs2", limite=100)
#estadisticas_partido('1-0faa6008-b45d-4651-ace8-0b1cdb1e1697',"d1a1aa41-f4ea-4035-97f7-cd522733c6d9")
#detalles_partido('1-0faa6008-b45d-4651-ace8-0b1cdb1e1697')