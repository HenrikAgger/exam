import React, { useState, useEffect } from "react";
import facade from "./apiFacade";

function Login(props) {
  const [user, setUser] = useState({ username: "", password: "" });

  const login = evt => {
    evt.preventDefault();
    console.log(user);

    props.login(user.username, user.password);
  };

  const onChange = evt => {
    setUser({ ...user, [evt.target.id]: evt.target.value });
  };

  return (
    <div>
      <h2>Login</h2>
      <form onSubmit={login} onChange={onChange}>
        <input placeholder="User Name" id="username" />
        <input placeholder="Password" id="password" />
        <button>Login</button>
      </form>
    </div>
  );
}

function LoggedIn() {
  const [data, setData] = useState({ msg: "Fetching!!" });

  useEffect(() => {
    facade.fetchData().then(res => setData(res));
  }, []);

  return (
    <div>
      <h2>Data Received from server</h2>
      <h3>{data.msg}</h3>
    </div>
  );
}

function App() {
  const [loggedIn, setLoggedIn] = useState(false);

  const logout = () => {
    facade.logout();
    setLoggedIn(false);
  };
  const login = (user, pass) => {
    facade.login(user, pass).then(res => setLoggedIn(true));
  };

  return (
    <div>
      <AllBikes />
      <FindBikes />
    </div>
  );
}

function AllBikes() {
  const [bikes, setBikes] = useState([]);

  useEffect(() => {
    facade.getAllBikes().then(res => setBikes(res));
  }, []);

  return (
    
    bikes.map(bike => 
      <table>
        <thead>
          <tr>
            <th>Bike Make</th>
            <th>Bike Size</th>
            <th>Bike Gender</th>
            <th>Bike Gears</th>
            <th>Bike Day Price</th> 
          </tr>
        </thead>
        <tbody>
              {bike.map(tbike => (
                <tr>
                  <td>{tbike.make}</td>
                  <td>{tbike.size}</td>
                  <td>{tbike.gender}</td>
                  <td>{tbike.gears}</td>
                  <td>{tbike.dayPrice}</td>
                </tr>
              ))}
        </tbody>
      </table>
    )
    
  )
}

function FindBikes() {
  const [id, setId] = useState(0);
  const [bike, setBike] = useState({});

  const onChange = evt => { 
    setId(evt.target.value);
  };

  const onSubmit = evt => {
    evt.preventDefault();
    facade.findBike(id).then(res => setBike(res));
  };

  return (
    <form onSubmit={onSubmit}> 
      <input type="number" id="id" placeholder="Id" onChange={onChange} />
      {bike.id}
    </form>
  );
}

export default App;
