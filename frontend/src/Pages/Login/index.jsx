import React from "react";
import { useDispatch } from "react-redux";
import { Link } from "react-router-dom";
import { login } from "../../app/actions/user.actions";
import LoginImage from "../../assets/loginpicture.png";

function Login() {
  const dispatch = useDispatch();
  const [username, setUsername] = React.useState("");
  const [password, setPassword] = React.useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    const user = {
      username,
      password,
    };
    dispatch(login(user));
  };

  return (
    <div className="container mt-5 mb-5 card" style={{ backgroundColor: "white", width: "60%", border:"solid", borderRadius: "10px", boxShadow: "0px 4px 10px rgba(0, 0, 0, 0.1)" }}>
      <div className="card-body">
        <div className="row">
          <div className="col-6">
            <div className="text-center">
              <h1 className="mt-1" style={{ color: "#333", fontFamily:"cursive", fontSize: "1.5rem", fontWeight: "bold"}}>Welcome to Fitness Pro</h1>
              <img
                src={LoginImage}
                className="image-fluid"
                alt="login"
                style={{ maxWidth: "100%", height: "100%", borderRadius: "10px" }}
              />
            </div>
          </div>
          <div className="col-6">
            <form onSubmit={handleSubmit}>
              <div className="mt-5 mb-3">
                <label className="form-label" style={{ color: "#555", fontSize: "1.2rem" }}>Username</label>
                <input
                  type="text"
                  className="form-control input-width-login"
                  value={username}
                  onChange={(e) => setUsername(e.target.value)}
                  style={{ borderRadius: "5px", border: "1px solid #ddd" }}
                />
                <div id="emailHelp" className="form-text" style={{ color: "#888", fontSize: "0.7rem" }}>
                  We'll never share your username with anyone else.
                </div>
              </div>
              <div className="mb-3">
                <label className="form-label" style={{ color: "#555", fontSize: "1.2rem" }}>Password</label>
                <input
                  type="password"
                  className="form-control input-width-login"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  style={{ borderRadius: "5px", border: "1px solid #ddd" }}
                />
              </div>

              <button type="submit" className="btn btn-primary" style={{ borderRadius: "5px", fontSize: "1.2rem", fontWeight: "bold" }}>
                LOGIN
              </button>
            </form>
            <hr />
            <Link to="/signup" className="text-decoration-none" style={{ color: "#555", fontSize: "1rem" }}>
              If you haven"t an account. Create new account
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Login;
