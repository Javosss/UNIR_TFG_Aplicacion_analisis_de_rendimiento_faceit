from flask import Flask, jsonify
from flask import request # Para pedir un parametro por Path Variable en la URL

from app.services import detalles_jugador_service
from app.services.detalles_jugador_service import estadisticas_jugador
from app.services import clasificaciones_service
from app.utils.peticion_HTTP import hacer_request
from app.services.partidos_jugador import partidos_jugador

# Crear la aplicación Flask
app = Flask(__name__)

# Crear las rutas de la API

#http://127.0.0.1:5000/api/player?nickname=s1mple
@app.route("/api/player", methods=["GET"]) # API de Extraer las partidas de ayer y KD medio, Kills media y partidas jugadas
def get_estadisticas_jugador():
    nickname = request.args.get('nickname') # Se pide el nickname por parametro
    stats = detalles_jugador_service.estadisticas_jugador(nickname) # Respuesta del JSON de la función desarrollada
    return jsonify(stats) # Se sube en JSON las stats

'''
http://127.0.0.1:5000/api/clasificacion?region=EU&juego=cs2
http://127.0.0.1:5000/api/clasificacion?region=EU&juego=cs2&pais=fr
'''
@app.route("/api/clasificacion", methods=["GET"]) # API para extraer la clasificación de jugadores en una región o país
def get_clasificacion_jugadores():
    # Se piden los parámetros de la API
    region = request.args.get('region')
    juego = request.args.get('juego')
    # Los parámetros opcionales
    pais = request.args.get('pais', None)
    comienzo = request.args.get('comienzo', None)
    limite = request.args.get('limite', None)

    clasificacion = clasificaciones_service.clasificacion_jugadores(region, juego, pais, comienzo, limite)
    return jsonify(clasificacion)

'''
http://127.0.0.1:5000/api/posicion?region=EU&juego=cs2&player_id=d1a1aa41-f4ea-4035-97f7-cd522733c6d9
http://127.0.0.1:5000/api/posicion?region=EU&juego=cs2&player_id=d1a1aa41-f4ea-4035-97f7-cd522733c6d9&pais=fr
'''
@app.route("/api/posicion", methods=["GET"])
def get_posicion_jugador_clasificacion(): # API para extraer la posicion de un jugador (player_id) en una región o país
    # Se piden los parámetros de la API
    juego = request.args.get('juego')
    region = request.args.get('region')
    player_id = request.args.get('player_id')
    # Los parámetros opcionales
    pais = request.args.get('pais')

    posicion_ranking = clasificaciones_service.posicion_jugador_clasificacion(juego, region, player_id, pais)
    return jsonify(posicion_ranking)

@app.route("/api/partidos_jugador", methods=["GET"])
def get_ultimos_partidos_jugador():
    player_id = request.args.get('player_id')
    juego = request.args.get('juego')

    partidos = partidos_jugador(player_id, juego)
    return jsonify(partidos)

# Ejecutar la aplicación Flask
if __name__ == "__main__":
    app.run(debug=True)