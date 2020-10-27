import axios from "axios";

function createInstance() {
  const instance = axios.create({
    baseURL: "http://localhost:8080/",
    headers: {
      "Content-Type": "application/json"
    }
  });
  return instance;
}
function createInstance2() {
  const instance = axios.create({
    baseURL: "http://localhost:5000/",
    headers: {
      "Content-Type": "application/json"
    }
  });
  return instance;
}

export { createInstance, createInstance2 };
