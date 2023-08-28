<h1>WebGallery App</h1>
The "WebGallery App" is a simple web-based application designed for users to manage their photo collections. This app provides a range of functionalities including user accounts, photos management, admin panel and more.

<details>
<summary><h2>Tech Stack</h2></summary>
<p1>&#x2022 <b>Java 17:</b> Programming language used for implementing the application.</p1><br><br>
<p1>&#x2022 <b>Spring Boot:</b> Framework used for building web applications.</p1><br><br>
<p1>&#x2022 <b>PostgreSQL:</b> Database for storing photos and user information.</p1>
</details>
<details>
  <summary><h2>API Endpoints</h2></summary>
  <p1>&#x2022 <b>'POST /register':</b> Register a new user account.</p1><br><br>
  <p1>&#x2022 <b>'POST /login':</b>  Login an existing user.</p1><br><br>
  <p1>&#x2022 <b>'POST /logout':</b> Logout the currently logged-in user.</p1><br><br>
  <p1>&#x2022 <b>'POST /user/image/upload':</b> Upload a new image to the user's gallery.</p1><br><br>
  <p1>&#x2022 <b>'GET /user/image/{uuid}':</b> Retrive an image with uuid that belongs to the logged-in user.</p1><br><br>
  <p1>&#x2022 <b>'GET /user/image/{uuid}/info':</b> Retrive an informations about an image with uuid that belongs to the logged-in user.</p1><br><br>
  <p1>&#x2022 <b>'GET /user/images':</b> Retrive all images that belongs to the logged-in user.</p1><br><br>
</details>
