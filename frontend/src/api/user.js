// userService.js
import store from "@/store";
import { createInstance } from "./index.js";

const instance = createInstance();

function findById(id, success, fail) {
  instance
    .get("/api/users/" + id)
    .then(success)
    .catch(fail);
}

function signup(
  email,
  name,
  password,
  company1,
  company2,
  company3,
  success,
  fail
) {
  const user = {
    email: email,
    name: name,
    password: password,
    company1: company1,
    company2: company2,
    company3: company3
  };

  instance
    .post("/api/user/signup", JSON.stringify(user))
    .then(success)
    .catch(fail);
}

function login(email, password, success, fail) {
  const body = {
    email: email,
    password: password
  };

  instance
    .post("/api/user/login", JSON.stringify(body))
    .then(success)
    .catch(fail);
}

function signout(email, success, fail) {
  instance
    .delete("api/user/" + email)
    .then(success)
    .catch(fail);
}

function update(
  email,
  name,
  password,
  company1,
  company2,
  company3,
  fail
) {
  const user = {
    email: email,
    name: name,
    password: password,
    company1: company1,
    company2: company2,
    company3: company3
  };
  instance
    .put("/api/user/update", JSON.stringify(user))
    .then(() =>{
      alert("회원정보가 수정되었습니다.");
      store.commit("setCompany1",company1);
      store.commit("setCompany2",company2);
      store.commit("setCompany3",company3);
    }
    )
    .catch(fail);
}

function save(data, id, success, fail, final) {
  instance
    .post("/api/users/upload/" + id, data, {
      headers: { "Content-Type": "multipart/form-data" }
    })
    .then(success)
    .catch(fail)
    .finally(final);
}

export { findById, signup, login, update, save, signout };
