import app.utils.peticion_HTTP
from app.utils.peticion_HTTP import hacer_request


def partidos_jugador(player_id, juego, desde=None, hasta=None, comienzo=None, limite=None):
    parametros = {"game" : juego, "from" : desde, "to" : hasta, "offset" : comienzo, "limit" : limite}
    cuerpo = hacer_request(f"players/{player_id}/history",parametros)

    i = 1
    lista_partidos = cuerpo["items"]
    for partido in lista_partidos:
        print(i, " :", partido)
        i+=1


partidos_jugador("d1a1aa41-f4ea-4035-97f7-cd522733c6d9","cs2", limite=100)