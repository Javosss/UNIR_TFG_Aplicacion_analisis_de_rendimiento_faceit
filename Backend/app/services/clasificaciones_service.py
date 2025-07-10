from app.utils.peticion_HTTP import hacer_request


'''
Funciones para sacar la clasificación de jugadores con más puntuación y funcion para sacar la posicion de un jugador 
en una clasificación ya sea por región o por país.
Se ha programado de tal forma que se puede pasar por parametros de la propia funcion las "restricciones". 
'''
# Funcion para sacar el top de jugadores de una Region (por ejemplo EU) de un juego (cs2 o csgo)
def clasificacion_jugadores(region,juego, pais=None,comienzo=None, limite=None):
    datos_devolver = [] # Lista para devolver

    # Posibles parametros: "country","offset","limit"
    country = pais # Clasificación por país
    offset = comienzo # Posicion por la que empieza al extraer la información
    limit = limite # Número de jugadores que extrae (El máximo es 100)
    parametros = {"country" : country, "offset" : offset, "limit" : limit}

    cuerpo = hacer_request(f"rankings/games/{juego}/regions/{region}", parametros)

    # Lista con la información que interesa sacar de la API
    lista_items = cuerpo["items"]
    # Se recorre la lista de items
    for item in lista_items:
        position = item["position"]
        nickname = item["nickname"]
        faceit_elo = item["faceit_elo"]
        player_id = item["player_id"]
        country = item["country"]

        jugador = {"posicion" : position, "nickname" : nickname, "faceit_elo" : faceit_elo, "player_id" : player_id, "country" : country}
        datos_devolver.append(jugador)
    return datos_devolver

def posicion_jugador_clasificacion(juego, region, player_id,country=None):
    lista_devolver = [] # Lista para devolver los datos

    parametros = {"country" : country}
    cuerpo = hacer_request(f"rankings/games/{juego}/regions/{region}/players/{player_id}", parametros)

    tipo_ranking = ""

    if country==None: # Si no se le pasa un país por parametro, el ranking es el de la region
        tipo_ranking = region
    else: # Si se le ha pasado el pais como parametro, el tipo de ranking será de ese pais
        tipo_ranking = country

    # Datos a sacar de la API
    posicion = cuerpo["position"]
    return posicion

#print(clasificacion_jugadores("EU","cs2"))
#posicion_jugador_clasificacion("cs2","EU","d1a1aa41-f4ea-4035-97f7-cd522733c6d9","fr")