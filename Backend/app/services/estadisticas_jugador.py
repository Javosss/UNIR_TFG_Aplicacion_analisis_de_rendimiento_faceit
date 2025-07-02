from app.services.partidos_jugador import *

from app.services.partidos_jugador import *

# Función para extraer y agrupar las estadísticas de un jugador en un número de partidos
def extraer_resumen_estadisticas_jugador(player_id, juego, num_partidos=20):

    ids = devolver_ids_partidos_jugador(player_id, juego, limite=1000) # Se obtienen los ids de las partidas del jugador (maximo 1000)
    if not ids:
        return {"mensaje": "No se encontraron partidas para el jugador"}

    ids = ids[:num_partidos] # Limite del análisis al número de partidas que se indique
    # Contadores y acumulador para acumular las estadísticas de cada partido para hacer el resumen de estadísticas
    acumuladores = {}
    total_partidos = 0
    victorias = 0
    mapas = {}

    # Se recorre cada partido y se acumulan las estadísticas relevantes
    for match_id in ids:
        stats = estadisticas_partido(match_id, player_id)
        # En algunos partidos, no se contiene información válida (Por ejemplo, probando a un jugador, tenia una partida en la que se fue su internet)
        # En esos casos, se omite el partido, porque también causa errores
        if not stats or stats["Kills"] is None:
            continue

        total_partidos += 1

        if stats["Result"] == "1": # Si el resultado es positivo, se cuenta la victoria
            victorias += 1

        # Se recorren todas las claves del diccionario de estadísticas del partido
        for clave, valor in stats.items():
            if clave in ["Nickname del jugador", "ID del equipo ganador", "Resultado del partido", "jugador_equipo"]: # Estas claves son texto. Se omiten
                continue
            # Se convierte a float, y si no es posible (por ejemplo si el valor es None o una string no numérica),
            # se ignora con un try
            try:
                valor = float(valor)
                if clave not in acumuladores:
                    acumuladores[clave] = 0.0
                acumuladores[clave] += valor
            except (ValueError, TypeError):
                continue

        # Agrupar estadísticas por mapa
        mapa = stats.get("Mapa", "Desconocido")
        if mapa not in mapas:
            # Se inicializa el diccionario para ese mapa si no existe aún (algunos mapas son de la comunidad y no los oficiales)
            mapas[mapa] = {
                "partidas": 0,
                "victorias": 0,
                "Kills": 0.0,
                "ADR": 0.0,
                "MVPs": 0.0
            }

        mapas[mapa]["partidas"] += 1
        if stats["Result"] == "1":
            mapas[mapa]["victorias"] += 1

        mapas[mapa]["Kills"] += float(stats.get("Kills", 0))
        mapas[mapa]["ADR"] += float(stats.get("ADR", 0))
        mapas[mapa]["MVPs"] += float(stats.get("MVPs", 0))

    if total_partidos == 0:
        return {"mensaje": "No se pudieron procesar estadísticas que sean válidas"}


    resumen = {
        "Partidos analizados": total_partidos,
        "Victorias": victorias,
        "Porcentaje de victorias": round((victorias / total_partidos) * 100, 2),
        "Estadísticas promedio por partido": {}
    }

    # Calcular los promedios de cada estadística numérica acumulada
    for clave, valor_total in acumuladores.items():
        resumen["Estadísticas promedio por partido"][clave] = round(valor_total / total_partidos, 2)

    # Calcular un resumen específico para cada mapa jugado
    resumen["Estadísticas por mapa"] = {}
    for mapa, datos in mapas.items():
        partidas = datos["partidas"]
        resumen["Estadísticas por mapa"][mapa] = {
            "Partidas jugadas": partidas,
            "Victorias": datos["victorias"],
            "Winrate (%)": round((datos["victorias"] / partidas) * 100, 2),
            "Kills promedio": round(datos["Kills"] / partidas, 2),
            "ADR promedio": round(datos["ADR"] / partidas, 2),
            "MVPs promedio": round(datos["MVPs"] / partidas, 2)
        }

    return resumen


#print(extraer_resumen_estadisticas_jugador("d1a1aa41-f4ea-4035-97f7-cd522733c6d9","cs2", 100))