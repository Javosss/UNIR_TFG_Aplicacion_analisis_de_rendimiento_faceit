import requests # Se importa la libreria requests para manejar las peticiones

from app.config import FACEIT_API_KEY, FACEIT_API_URL # Archivo config para manejar la API Key como variable de entorno

#Funcion para hacer la request a las APIs de FACEIT
def hacer_request(endpoint, parametros):
    url = f"{FACEIT_API_URL}/{endpoint}" # Se le añade el endpoint a la URL base
    headers = {"Authorization": f"Bearer {FACEIT_API_KEY}"} # Cabeza de la funcion
    params = parametros # Parametros de la peticion (a veces opcionales)
    response = requests.get(url, headers=headers, params=params) # Cuerpo que devuelve la peticion a la API

    if response.status_code == 200: # Si se recibe un codigo 200 (OK), se devuelve el cuerpo en JSON
        return response.json()
    else:
        return response.status_code
