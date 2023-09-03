import { useAuth } from './hooks/useAuth';

const LogoutButton = () => {
  const { logout } = useAuth();

  return (
    <div>
      <h1>Logout</h1>
      <button onClick={logout}>Logout</button>
    </div>
  );
};

export default LogoutButton;