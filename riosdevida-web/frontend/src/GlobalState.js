import React, { createContext, useState, useContext } from "react";

// Create Contexts for Global State and Update Functions
const GlobalStateContext = createContext();
const GlobalStateUpdateContext = createContext();

// Custom hooks to use the contexts
export const useGlobalState = () => useContext(GlobalStateContext);
export const useGlobalStateUpdate = () => useContext(GlobalStateUpdateContext);

export const GlobalStateProvider = ({ children }) => {
  const [language, setLanguage] = useState("def");
  const [location, setLocation] = useState("def");
  const [fetchedData, setFetchedData] = useState(null); // State to store fetched data
 	
  const fetchDataFromServer = (language, location) => {
    fetch(`/riosdevida-web/api/content/${location}/${language}`)
      .then((response) => response.json())
      .then((data) => {
        setFetchedData(data); // Store fetched data in global state
      })
      .catch((error) => console.error("Error fetching data:", error));
  };
  
  const updateLanguage = (newLanguage) => {
    setLanguage(newLanguage);
    fetchDataFromServer(newLanguage, location); // Fetch data whenever language changes
  };

  const updateLocation = (newLocation) => {
    setLocation(newLocation);
    fetchDataFromServer(language, newLocation); // Fetch data whenever location changes
  };
  
  React.useEffect(() => {
	fetchDataFromServer("def", "def"); 
  },[]);
  

  return (
    <GlobalStateContext.Provider value={{ language, location, fetchedData }}>
      <GlobalStateUpdateContext.Provider value={{ updateLanguage, updateLocation}}>
        {children}
      </GlobalStateUpdateContext.Provider>
    </GlobalStateContext.Provider>
  );
};
