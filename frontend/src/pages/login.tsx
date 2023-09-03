import React, { useState } from 'react';
import { useAuth } from '../hooks/useAuth';

const Login = () => {

  const { login } = useAuth();
  const [user, setUser] = useState({
    username: '',
    password: '',
  });

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setUser((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleLogin = (e : React.MouseEvent<HTMLButtonElement>) => {
    e.preventDefault();
    fetch("http://localhost:8080/login", {
      method: "POST",
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        username: user.username,
        password: user.password
      }),
    })
    .then((response) => response.json())
    .then((result) => {
      console.log(result);
      login(result)
    });
  };

  const handleRegister = () => {
    // Przejście do formularza rejestracji lub odpowiednia obsługa rejestracji
    // Przykładowo, można przekierować użytkownika do innej ścieżki w aplikacji
    console.log('Przejdź do formularza rejestracji');
  };

  return (
    <div>
      <h1>Login</h1>
      <form>
        <div className="form-group">
          <label htmlFor="username">Nazwa użytkownika</label>
          <input
            type="text"
            className="form-control"
            id="username"
            name="username"
            value={user.username}
            onChange={handleInputChange}
          />
        </div>
        <div className="form-group">
          <label htmlFor="password">Hasło</label>
          <input
            type="password"
            className="form-control"
            id="password"
            name="password"
            value={user.password}
            onChange={handleInputChange}
          />
        </div>
        <button
          type="button"
          className="btn btn-primary"
          onClick={handleLogin}
        >
          Login
        </button>
        <button
          type="button"
          className="btn btn-link"
          onClick={handleRegister}
        >
          Zarejestruj się
        </button>
      </form>
    </div>
  );
};

export default Login;