//const URL = "http://localhost:8080/exam";
const URL = "https://henriksdomainname.dk/exam"; 

function handleHttpErrors(res) {
  if (!res.ok) {
    return Promise.reject({ status: res.status, fullError: res.json() });
  }
  return res.json();
}
function ApiFacade() {
  //Insert utility-methods from a latter step (d) here

  const makeOptions = (method, addToken, body) => {
    var opts = {
      method: method,
      headers: {
        "Content-type": "application/json",
        Accept: "application/json"
      }
    };
    if (addToken && loggedIn()) {
      opts.headers["x-access-token"] = getToken();
    }
    if (body) {
      opts.body = JSON.stringify(body);
    }
    return opts;
  };
  const setToken = token => {
    localStorage.setItem("jwtToken", token);
  };
  const getToken = token => {
    return localStorage.getItem("jwtToken");
  };
  const loggedIn = () => {
    const loggedIn = getToken() != null;
    return loggedIn;
  };
  const logout = () => {
    localStorage.removeItem("jwtToken");
  };

  const login = (user, pass) => {
    const options = makeOptions("POST", true, {
      username: user,
      password: pass
    });
    return fetch(URL + "/api/login", options)
      .then(handleHttpErrors)
      .then(res => {
        setToken(res.token);
      });
  };
  const fetchData = () => {
    const options = makeOptions("GET", true);
    return fetch(URL + "/api/info/user", options).then(handleHttpErrors);
  };

  const getAllBikes = () => {
    return fetch(URL + "/api/bike/all").then(handleHttpErrors);
  };

  const findBike = (id) => {
    return fetch(URL + "/api/bike/id/" + id).then(handleHttpErrors);
  };

  const createBike = (bike) => {
    return fetch(URL + "/api/bike/create", makeOptions("POST", false, bike)).then(handleHttpErrors);
  };

  const editBike = (bike) => {
    return fetch(URL + "/api/bike/edit", makeOptions("PUT", false, bike)).then(handleHttpErrors);
  };  

  const deleteBike = (id) => {
    return fetch(URL + "/api/bike/delete/" + id).then(handleHttpErrors);
  };  


  return {
    logout,
    login,
    fetchData,
    getAllBikes,
    findBike,
    createBike,
    editBike,
    deleteBike,
  };
}

const facade = new ApiFacade();
export default facade;