/*import { useEffect } from "react";
import { useUser, User } from "./useUser";
import { useLocalStorage } from "./useLocalStorage";

export const useAuth = () => {
  const { user, addUser, removeUser } = useUser();
  const { getItem } = useLocalStorage();

  useEffect(() => {
    const user = getItem("user");
    if(user) {
      addUser(JSON.parse(user));
    }
  }, []);

  const login = (user : User ) => {
    addUser(user);
  };

  const logout = () => {
    removeUser();
  };

  return { user, login, logout, setUser };
};*/

import { useEffect, useState } from "react";
import { useUser, User } from "./useUser";
import { useLocalStorage } from "./useLocalStorage";

export const useAuth = () => {
  const { user: currentUser, addUser, removeUser } = useUser();
  const { getItem} = useLocalStorage();
  const [user, setUser] = useState<User | null>(null);

  useEffect(() => {
    const storedUser = getItem("user");
    if (storedUser) {
      addUser(JSON.parse(storedUser));
    }
  }, []);

  useEffect(() => {
    setUser(currentUser);
  }, [currentUser]);

  const login = (newUser: User) => {
    addUser(newUser);
  };

  const logout = () => {
    removeUser();
  };

  return { user, login, logout, setUser }; 
};
