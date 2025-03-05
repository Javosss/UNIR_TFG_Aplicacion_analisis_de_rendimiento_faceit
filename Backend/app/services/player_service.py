from app.utils.peticion_HTTP import hacer_request

def estadisticas_jugador(nickname):
    # Parámetros correctamente formateados
    parametros = {"nickname": nickname, "game": "cs2"}  # "game" es opcional
    cuerpo = hacer_request("players", parametros)
    print(cuerpo)
    # Extraer información básica del jugador
    nickname = cuerpo["nickname"]
    player_id = cuerpo["player_id"]
    pais = cuerpo["country"]
    avatar =cuerpo["avatar"]
    steam_id_64 = cuerpo["steam_id_64"]
    fecha_creacion_cuenta = cuerpo["activated_at"]

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
        "fecha_creacion_cuenta": fecha_creacion_cuenta,
    }
    return lista_devolver


estadisticas_jugador("Javos")