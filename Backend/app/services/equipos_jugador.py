from app.utils.peticion_HTTP import hacer_request
from datetime import datetime # Librería para formatear la fecha

# Función para extraer todos los equipos asociados al jugador (únicamente se guardan las ids, en las siguientes funciones se extrae la información de cada una)
def extraer_equipos_jugador(player_id, limite=None):
    params = { "limit": limite }
    respuesta = hacer_request(f"players/{player_id}/teams", params)
    items = respuesta["items"]
    lista_equipos = []

    for item in items:
        id_equipo = item["team_id"]
        lista_equipos.append(id_equipo)

    return lista_equipos

# Obtener los detalles de un equipo (se aplicará luego a todas las ids de la lista extraidas en la función extraer_equipo_jugador)
def detalles_del_equipo(team_id):

    cuerpo = hacer_request(f"teams/{team_id}")

    # Primero extraemos los miembros para separarlos
    miembros = cuerpo.get("members", [])

    # Creamos el diccionario plano con el resto de campos
    equipo_data = {
        "Nickname equipo": cuerpo.get("nickname"),
        "Nombre equipo": cuerpo.get("name"),
        "Juego": cuerpo.get("game"),
        "ID Equipo": cuerpo.get("team_id"),
        "Tipo equipo": cuerpo.get("team_type"),
        "Lider": cuerpo.get("leader"),
        "Foto del equipo": cuerpo.get("avatar"),
        "Descripcion": cuerpo.get("description"),
        "Twitter": cuerpo.get("twitter"),
        "Pagina del equipo": cuerpo.get("website"),
        "Facebook": cuerpo.get("facebook"),
        "Youtube": cuerpo.get("youtube"),
        "CoverImage": cuerpo.get("cover_image"),
        "Chat room ID": cuerpo.get("chat_room_id"),
        "Faceit URL": cuerpo.get("faceit_url"),
        "Total miembros": len(miembros),
        "Miembros": miembros  # Lista de diccionarios con info de cada miembro
    }

    # Se eliminan campos vacíos o nulos de la respuesta
    equipo_data = {k: v for k, v in equipo_data.items() if v is not None and (k == "Miembros" or v != "")}
    return equipo_data





def torneos_jugados_del_equipo(id_equipo):
    cuerpo = hacer_request(f"teams/{id_equipo}/tournaments")
    torneos_devolver = []
    items = cuerpo["items"]

    for item in items:
        nombre_torneo = item["name"]
        region = item["region"]
        juego = item["game_id"]
        tipo_partido = item["match_type"]
        numero_jugadores = item["number_of_players_joined"]
        url_torneo = item["faceit_url"]

        hora_comienzo = item["started_at"]
        hora_formateada = datetime.fromtimestamp(hora_comienzo).strftime("%Y-%m-%d %H:%M:%S")

        datos_torneo = {
            "Nombre del torneo": nombre_torneo,
            "Juego": juego,
            "Tipo partido": tipo_partido,
            "Region": region,
            "Numero de jugadores": numero_jugadores,
            "Hora de inicio": hora_formateada,
            "URL del torneo": url_torneo
        }
        torneos_devolver.append(datos_torneo)
    return torneos_devolver

# Función para juntar el funcionamiento de las funciones anteriores para compactarlo todo en uno (se juntan todos los endpoints anteriores en uno solo que se modelará en Java)
def obtener_equipos_completos(player_id, limite=None):
    ids_equipos = extraer_equipos_jugador(player_id, limite)
    resultado_final = []

    for equipo_id in ids_equipos:
        detalles_equipo = detalles_del_equipo(equipo_id)

        if detalles_equipo is not None:
            detalles_equipo["Torneos"] = torneos_jugados_del_equipo(equipo_id)
            resultado_final.append(detalles_equipo)

    return resultado_final

#print(extraer_equipos_jugador("a362c5ee-705b-4c7d-bf5f-c616e47ada19",100))
#extraer_equipos_jugador("d682a62e-43f5-49be-b8c9-de923adcf564",100)
#detalles_del_equipo("108a5c12-2252-4309-a315-c3d63d735c83")
#print(obtener_equipos_completos("a362c5ee-705b-4c7d-bf5f-c616e47ada19"))
#print(torneos_jugados_del_equipo("108a5c12-2252-4309-a315-c3d63d735c83"))