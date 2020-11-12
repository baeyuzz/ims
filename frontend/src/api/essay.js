import { createInstance } from "./index.js";
import store from "@/store";

const instance = createInstance();
function saveEssay() {
  const essay = {
    content: store.state.content,
    result1: store.state.result[0],
    result2: store.state.result[1],
    result3: store.state.result[2],
    result4: store.state.result[3],
    result5: store.state.result[4],
    result6: store.state.result[5],
    result7: store.state.result[6],
    result8: store.state.result[7],
    email: store.state.email
  };
  instance
    .post("/api/essay/makeEssay/", essay)
    .then((res) => {
      alert("저장 되었습니다")
      store.commit("setEssayId", res.data.message)
    }
    )
    .catch(() => alert("저장에 실패 했습니다"));
}

function getEssayByUser(userId, success, fail) {
  instance
    .get("/api/essay/getEssayByUser/" + userId)
    .then(success)
    .catch(fail);
}

function deleteEssay(id, success, fail) {
  instance
    .delete("/api/essay/" + id)
    .then(success)
    .catch(fail);
}

function shareEssay() {
  saveEssay();

  setTimeout(function () {
    instance
      .post("/api/essay/share/" + store.state.essayId)
      .then(
        () =>
          alert("공유되었습니다")
      )
      .catch(
        (err) =>
          alert(err, "실패")
      );
  }, 1000);

}


function recommendCompany(rank1, rank2) {

  instance
      .get(`/api/company/select?rank1=${rank1}&rank2=${rank2}`)
      .then((res) => {
          store.commit("setCompany", res.data);
      }
      )
      .catch((err) => (
          console.log(err)
      )
      );
}



export { saveEssay, getEssayByUser, deleteEssay, shareEssay, recommendCompany };
