import { useContext } from 'react';
import { AuthContext } from './context/AuthContext';
import { useAuth } from './hooks/useAuth';
import Login from './pages/login';
import Logout from './LogoutButton';
import 'bootstrap/dist/css/bootstrap.min.css';

const App = () => {

  const { user, login, logout, setUser } = useAuth();

  const isUserLoggedIn = localStorage.getItem('user');

  return (
    <AuthContext.Provider value={{ user, setUser }}>
      <div className='App'>
        {isUserLoggedIn ? (
          <Logout/ >
        ) : (
          <Login />
        )}
      </div>
    </AuthContext.Provider>
  );
};

export default App;
