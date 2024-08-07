# API RESTful de creación de usuarios. Evaluación JAVA
## Descripción
Este proyecto expone un API RESTful que permite crear y consultar usuarios. Se ha usando una Base de datos H2 en memoria para almacenar los datos de los usuarios.

## Controllers
### - UserController:  
http://localhost:8087/api/v1/auth
- **POST** 
  - **Descripción:** Crea un usuario
  - **Url:** /register
    - **Request Body:**
      ```json
         {
          "name": "francisco",
          "email": "juanjrohad@sr4igw2uez1.com",
          "password": "Password1@",
          "phones": [
              {
                  "number": "1234567",
                  "citycode": "1",
                  "contrycode": "57"
              }
           ]
         }
      ```
    - **Response Body:**
        ```json
         {
          "id": 1,
          "created": "2024-08-07T05:26:59.589+00:00",
          "modified": "2024-08-07T05:26:59.589+00:00",
          "lastLogin": "2024-08-07T05:26:59.589+00:00",
          "token": "eyJhbGciOiJIUzI1NiJ9.eyJpc0FjdGl2ZSI6dHJ1ZSwic3ViIjoianVhbmpyb2hhZEBzcjRpZ3cydWV6MS5jb20iLCJpYXQiOjE3MjMwMDg0MTksImV4cCI6MTcyMzA0NDQxOX0.ZwnSRamqUQodYP7p80CosNzB4rZRX-DMnUzCvP6CbME",
          "isActive": true
         }
        ```
    - **Exceptions:**
    
       Para el manejo de excepciones se ha creado una clase GlobalExceptionHandler que se encarga de manejar las excepciones de la aplicación.  
       por ejemplo *EmailAlreadyExistsException* que se llama cuando el email ya existe en la base de datos.
        estas clases responden una clase *ApiResponse* con un formato:
      ```json
      {
       "mensaje": "El correo ya registrado"
      }
      ```
      ```json
      {
       "mensaje": "name es obligatorio"
      }
      ```
      ```json
      {
       "mensaje": "debe ser una dirección de correo electrónico con formato correcto, email no es válido"
      }
      ```

    - **JWT:**

      Se ha usado la dependencia io.jsonwebtoken para el manejo de tokens y se ha creado una clase *JwtUtil* que se encarga de la creación y validación de los tokens.
      Dicho token se genera con la información del usuario: email y estado; expira en 5 minutos.
      Y el token es persistido en la base de datos para su posterior validación.

    - **Swagger:**
    http://localhost:8087/swagger-ui/index.html
  

  - **Validaciones:**
    - El name no debe estar en blanco.
    - El email no debe estar en blanco.
    - El email debe tener un valor válido segun exp: "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
    - El password no debe estar en blanco.
    - El email debe de tener un valor válido segun exp: "^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$"
      - El password debe tener al menos una mayúscula, una minúscula, un número y un caracter especial: @$!%*?&.
      - El password debe tener una longitud mínima de 8 caracteres.
  

- **GET** /users
  - **Descripción:** Obtiene la informacion de los usuarios de la BD.
  -  **Response Body:**
    ```json
    [
	  {
		"id": 1,
		"created": "2024-08-07T05:26:59.589+00:00",
		"modified": "2024-08-07T05:26:59.589+00:00",
		"lastLogin": "2024-08-07T05:26:59.589+00:00",
		"token": "eyJhbGciOiJIUzI1NiJ9.eyJpc0FjdGl2ZSI6dHJ1ZSwic3ViIjoianVhbmpyb2hhZEBzcjRpZ3cydWV6MS5jb20iLCJpYXQiOjE3MjMwMDg0MTksImV4cCI6MTcyMzA0NDQxOX0.ZwnSRamqUQodYP7p80CosNzB4rZRX-DMnUzCvP6CbME",
		"isActive": true,
		"name": "fran",
		"email": "juanjrohad@sr4igw2uez1.com",
		"phones": [
			    {
				"number": "1234567",
				"citycode": "1",
				"contrycode": "57"
			    }
		  ]
      }
    ]
    ```
  
