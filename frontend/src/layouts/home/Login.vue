<template>
    <v-card>
        <v-container>
            <v-card-title class="headline">LogIn</v-card-title>

            <v-text-field
                label="이메일"
                v-model="user.email"
            ></v-text-field>
            <v-text-field
                label="비밀번호"
                v-model="user.password"
                type="password"
            ></v-text-field>

            <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="green darken-1" text @click="login" min-width="100px">
                    <v-icon>power_settings_new</v-icon>
                    LogIn
                </v-btn>
                <v-btn text @click.stop="signup = true" min-width="100px">
                    <v-icon>person_add</v-icon>
                    Sign Up
                </v-btn>
                <v-dialog v-model="signup" max-width="500">
                    <Signup></Signup>
                </v-dialog>
            </v-card-actions>
        </v-container>
    </v-card>

</template>

<script>
import Signup from "./Signup";
export default {
    data() {
        return {
            signup: false,
            user: {
                email: "",
                password: ""
            }
        };
    },
    components: {
        Signup
    },
    methods: {
        login() {
            const scope = this;

            login(
                this.user.email,
                this.user.password,
                function (response) {
                    scope.$store.commit("setIsSigned", true);
                    scope.$store.commit("setUserId", response.data.id);

                    findWallet(
                        response.data.id,
                        function (response) {
                            if (response.status == 200) {
                                scope.$store.commit("setWalletAddress", response.data.address);
                            } else {
                                alert("Unexpected status code: " + response.status);
                            }
                            // 창 닫기
                            location.reload();
                        },
                        function (err) {
                            if (err.response != 404) {
                                console.error(err);
                                //alert("지갑 정보를 찾지 못했습니다.");
                            }
                        }
                    );
                },
                function (error) {
                    console.error(error);
                    alert("유저 이메일 혹은 비밀번호가 일치하지 않습니다.");
                }
            );
        }
    }
};
</script>

<style></style>
