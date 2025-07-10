from app.utils.peticion_HTTP import hacer_request
from datetime import datetime # Librería para formatear la fecha

def estadisticas_jugador(nickname):
    # Parámetros correctamente formateados
    parametros = {"nickname": nickname, "game": "cs2"}  # "game" es opcional
    cuerpo = hacer_request("players", parametros)

    # Extraer información básica del jugador
    nickname = cuerpo["nickname"]
    player_id = cuerpo["player_id"]
    pais = cuerpo["country"]
    avatar =cuerpo["avatar"]
    steam_id_64 = cuerpo["steam_id_64"]

    fecha_creacion_cuenta = cuerpo["activated_at"]
    # Formatear la fecha. Es necesario hacerlo en un bloque try porque si no da muchos errores a la hora de formatear
    try:
        fecha_creacion = datetime.strptime(fecha_creacion_cuenta, "%Y-%m-%dT%H:%M:%S.%fZ")
    except ValueError:
        fecha_creacion = datetime.strptime(fecha_creacion_cuenta, "%Y-%m-%dT%H:%M:%SZ") # En caso de que falle, se intenta sin milisegundos
    fecha_formateada = fecha_creacion.strftime("%d/%m/%Y") # Formato al que se quiere cambiar. En este caso decido el formato europeo y sin horas/segundos

    # Información de CSGO en específico:
    stats_csgo = cuerpo["games"]["csgo"]
    region_csgo = stats_csgo["region"]
    nivel_csgo = stats_csgo["skill_level"]
    elo_csgo = stats_csgo["faceit_elo"]
    #Información de CS2 en específico:
    stats_cs2 = cuerpo["games"]["cs2"]
    region_cs2 = stats_cs2["region"]
    nivel_cs2 = stats_cs2["skill_level"]
    elo_cs2 = stats_cs2["faceit_elo"]

    # Lista para devolver
    lista_devolver = {
        "nickname": nickname,
        "player_id": player_id,
        "pais": pais,
        "avatar": avatar,
        "region_csgo": region_csgo,
        "nivel_csgo": nivel_csgo,
        "elo_csgo": elo_csgo,
        "region_cs2": region_cs2,
        "nivel_cs2": nivel_cs2,
        "elo_cs2": elo_cs2,
        "steam_id_64": steam_id_64,
        "fecha_creacion_cuenta": fecha_formateada,
    }
    return lista_devolver


# Función que devuelve el nickname del jugador a partir del ID de jugador
def devolver_nickname_jugador(player_id):
    cuerpo = hacer_request(f"players/{player_id}")  # Quizás el endpoint debería ser f"players/{player_id}"
    nickname = cuerpo["nickname"]
    return nickname


#print(estadisticas_jugador("Javos"))
#devolver_nickname_jugador("d1a1aa41-f4ea-4035-97f7-cd522733c6d9")