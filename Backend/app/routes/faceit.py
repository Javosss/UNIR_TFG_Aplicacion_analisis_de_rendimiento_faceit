from flask import Flask, jsonify
from flask import request # Para pedir un parametro por Path Variable en la URL

from app.services import player_service
from app.services.player_service import estadisticas_jugador


# Crear la aplicación Flask
app = Flask(__name__)

# Crear las rutas de la API

#http://127.0.0.1:5000/api/player?nickname=Javos
@app.route("/api/player", methods=["GET"]) # API de Extraer las partidas de ayer y KD medio, Kills media y partidas jugadas
def get_estadisticas_jugador():
    nickname = request.args.get('nickname') # Se pide el nickname por parametro
    stats = player_service.estadisticas_jugador(nickname) # Respuesta del JSON de la función desarrollada
    return jsonify(stats) # Se sube en JSON las stats


# Ejecutar la aplicación Flask
if __name__ == "__main__":
    app.run(debug=True)