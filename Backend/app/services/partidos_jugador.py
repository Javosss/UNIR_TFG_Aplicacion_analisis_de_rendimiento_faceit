import app.utils.peticion_HTTP
from app.utils.peticion_HTTP import hacer_request
from datetime import datetime

# Función para extraer los 100 últimos partidos de un jugador junto con algunos detalles básicos del partido
# Esta extracción no devuelve datos detallados del partido, para ello hay otro endpoint de la API de FACEIT
def partidos_jugador(player_id, juego, desde=None, hasta=None, comienzo=None, limite=None):
    parametros = {"game" : juego, "from" : desde, "to" : hasta, "offset" : comienzo, "limit" : limite}
    cuerpo = hacer_request(f"players/{player_id}/history",parametros)
    #print(cuerpo)

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
    #print(cuerpo)

    # Se extraen los ids de los partidos del jugador y se guardan en una lista
    ids_partidos = []
    for partido in cuerpo["items"]:
        match_id = partido["match_id"]
        ids_partidos.append(match_id)  # Añadir ID a la lista

    return ids_partidos



# Función para extraer estadísticas de un partido jugado
def estadisticas_partido(match_id, player_id=None):
    cuerpo = hacer_request(f"matches/{match_id}/stats")
    #print(cuerpo)
    # Diccionario con todas las estadísticas necesarias
    datos = {
        'Mapa': None,
        'Resultado del partido': None,
        'ID del equipo ganador': None,
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

    if not cuerpo or "rounds" not in cuerpo:
        return datos
    ''' 
    Para esta ruta es necesario hacer el mapeo manual, porque sin ello, la API no devolvía correctamente los campos del JSON
    Había muchos campos que salían null cuando no debería, incluso algunos básicos como kills/deaths, lo cuál era por este motivo'''

    mapeo_estadisticas = { # El mapeo se hace manualmente (las agrupo en las diferentes categorías para que sea más fácil de leer)
        # Estadísticas básicas
        "Kills": "Kills",
        "Deaths": "Deaths",
        "Assists": "Assists",
        "Headshots": "Headshots",
        "Headshots %": "Headshots %",
        "K/D Ratio": "K/D Ratio",
        "K/R Ratio": "K/R Ratio",
        "ADR": "ADR",
        "Damage": "Damage",
        "MVPs": "MVPs",
        "Result": "Result",

        # Clutches
        "1v1Count": "1v1Count",
        "1v1Wins": "1v1Wins",
        "1v2Count": "1v2Count",
        "1v2Wins": "1v2Wins",
        "Match 1v1 Win Rate": "Match 1v1 Win Rate",
        "Match 1v2 Win Rate": "Match 1v2 Win Rate",
        "Clutch Kills": "Clutch Kills",

        # Entries
        "Entry Count": "Entry Count",
        "Entry Wins": "Entry Wins",
        "Match Entry Rate": "Match Entry Rate",
        "Match Entry Success Rate": "Match Entry Success Rate",
        "First Kills": "First_Kills",

        # Multi-kills
        "Double Kills": "Double_Kills",
        "Triple Kills": "Triple Kills",
        "Quadro Kills": "Quadro Kills",
        "Penta Kills": "Penta Kills",

        # Armas especificas
        "Pistol Kills": "Pistol Kills",
        "Sniper Kills": "Sniper Kills",
        "Sniper Kill Rate per Match": "Sniper Kill Rate per Match",
        "Sniper Kill Rate per Round": "Sniper Kill Rate per Round",
        "Knife Kills": "Knife Kills",
        "Zeus Kills": "Zeus Kills",

        # Flashbangs
        "Flash Count": "Flash Count",
        "Flash Successes": "Flash Successes",
        "Flash Success Rate per Match": "Flash Success Rate per Match",
        "Enemies Flashed": "Enemies Flashed",
        "Enemies Flashed per Round in a Match": "Enemies Flashed per Round in a Match",
        "Flashes per Round in a Match": "Flashes per Round in a Match",

        # Utilidades
        "Utility Count": "Utility Count",
        "Utility Successes": "Utility Successes",
        "Utility Success Rate per Match": "Utility Success Rate per Match",
        "Utility Damage": "Utility Damage",
        "Utility Damage per Round in a Match": "Utility Damage per Round in a Match",
        "Utility Damage Success Rate per Match": "Utility Damage Success Rate per Match",
        "Utility Enemies": "Utility Enemies",
        "Utility Usage per Round": "Utility Usage per Round"
    }

    # Procesar todas las rondas (normalmente solo hay una en CS)
    for ronda in cuerpo["rounds"]:
        # Obtener información básica del partido
        estadisticas_ronda = ronda["round_stats"]
        datos['Mapa'] = estadisticas_ronda["Map"]
        datos['Resultado del partido'] = estadisticas_ronda["Score"]
        datos['ID del equipo ganador'] = estadisticas_ronda["Winner"]

        # Procesar equipos
        for equipo in ronda["teams"]:
            for jugador in equipo["players"]:
                if jugador["player_id"] == player_id:

                    datos['Nickname del jugador'] = jugador["nickname"]
                    datos['jugador_equipo'] = equipo["team_id"]

                    # Procesar las estadísticas del jugador
                    estadisticas_jugador = jugador["player_stats"]
                    for nombre, valor in estadisticas_jugador.items():
                        clave = mapeo_estadisticas.get(nombre)
                        if clave and clave in datos:
                            datos[clave] = valor

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


#print(partidos_jugador("d1a1aa41-f4ea-4035-97f7-cd522733c6d9","cs2", limite=100))
#devolver_ids_partidos_jugador("d1a1aa41-f4ea-4035-97f7-cd522733c6d9","cs2", limite=100)
#print(estadisticas_partido('1-75bff51d-c0c6-4ab1-9e4c-4e4cef676732',"d1a1aa41-f4ea-4035-97f7-cd522733c6d9"))
#print(detalles_partido('1-0faa6008-b45d-4651-ace8-0b1cdb1e1697'))
