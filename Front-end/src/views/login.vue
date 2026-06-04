<template>
    <v-img
        src="../assets/background.jpg" 
        height="100vh"
        class="d-flex align-center justify-center"
    >
        <v-card 
            class="auth-card rounded-xl pa-6 elevation-16 mx-auto"
            max-width="420"
            width="100%"
            style="background: rgba(255, 255, 255, 0.92); backdrop-filter: blur(12px);"
        >
            <v-card-title class="layout justify-center flex-column pb-4">
                <div class="d-flex align-center mb-2">
                    <v-icon size="40" color="#FF9062" class="mr-2">mdi-email-fast</v-icon>
                    <h1 class="text-h4 font-weight-bold" style="color: #2d3142; letter-spacing: -0.5px;">MailSphere</h1>
                </div>
                <span class="text-subtitle-2 text-center text--secondary">Access your secure workspace inbox</span>
            </v-card-title>

            <v-divider class="mb-4"></v-divider>

            <v-card-text class="pt-0">
                <v-form @submit.prevent="CheckLogin">
                    <v-col cols="12" class="py-1">
                        <label class="text-subtitle-2 font-weight-medium mb-1 d-block text--primary">E-mail Address</label>
                        <v-text-field
                            v-model="EmailAddress"
                            :error-messages="emailErrors"
                            required
                            outlined
                            dense
                            prepend-inner-icon="mdi-email-outline"
                            placeholder="username@domain.com"
                            class="rounded-lg"
                            @input="$v.EmailAddress.$touch()"
                            @blur="$v.EmailAddress.$touch()"
                        ></v-text-field>
                    </v-col>

                    <v-col cols="12" class="py-1">
                        <label class="text-subtitle-2 font-weight-medium mb-1 d-block text--primary">Password</label>
                        <v-text-field
                            v-model="password"
                            :error-messages="passwordErrors"
                            :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
                            :rules="[rules.required, rules.min]"
                            :type="showPassword ? 'text' : 'password'"
                            name="input-10-1"
                            hint="At least 8 characters"
                            counter
                            required
                            outlined
                            dense
                            prepend-inner-icon="mdi-lock-outline"
                            placeholder="Enter your password"
                            class="rounded-lg"
                            @input="$v.password.$touch()"
                            @blur="$v.password.$touch()"
                            @click:append="showPassword = !showPassword"
                        ></v-text-field>
                    </v-col>

                    <v-divider class="my-4"></v-divider>

                    <v-card-actions class="d-flex flex-column gap-2 px-0">
                        <v-btn 
                            color="#FF9062" 
                            dark
                            block
                            large
                            elevation="2"
                            class="rounded-lg font-weight-bold"
                            style="text-transform: none; font-size: 1.05rem;"
                            type="submit"
                            :loading="loading"
                        >
                            Sign In
                        </v-btn>
                        
                        <div class="text-center mt-4 w-100">
                            <span class="text-caption text--secondary mr-1">Don't have an account?</span>
                            <router-link to="/signup" class="text-caption font-weight-bold" style="color: #FF9062; text-decoration: none;">Create one here</router-link>
                        </div>
                    </v-card-actions>
                </v-form>
                
                <v-snackbar
                    v-model="snackbar"
                    timeout="3000"
                    rounded="pill"
                    color="grey darken-3"
                >
                    {{ message }}
                    <template v-slot:action="{ attrs }">
                        <v-btn
                            color="#FF9062"
                            text
                            v-bind="attrs"
                            @click="snackbar = false"
                        >
                            Close
                        </v-btn>
                    </template>
                </v-snackbar>
            </v-card-text>
        </v-card>
    </v-img>
</template>

<script>

import Vue from 'vue'
import axios from 'axios'
import VueAxios from 'vue-axios'
import { validationMixin } from "vuelidate";
import { required, maxLength, email } from "vuelidate/lib/validators";

export default {
    mixins: [validationMixin],
    validations: {
        EmailAddress: { required, email },
        password: { required },
    },
    data: () => ({
        EmailAddress : '',
        password : '',
        showPassword : false ,
        snackbar : false ,
        message : '',
        loading : false ,
        rules: {
        required: value => !!value || 'Required.',
        min: v => v.length >= 8 || 'Min 8 characters',
        emailMatch: () => (`The email and password you entered don't match`),
        },   
    }),
    methods : {
        CheckLogin : function(){
            this.loading = true ;
            axios.get('http://localhost:8080/api/login',{
                params: {
                    Emailaddress : this.EmailAddress,
                    password : this.password,
                }
            }).then(Response=>{
                const Data = Response.data;
                this.message = Data ;
                this.snackbar = true ;
                if(this.message == "Successfully Log in"){
                    setTimeout(this.gomainpage, 1000);
                }
            }).finally(()=>{
                this.loading = false ;
            });
        },
        gomainpage(){
            this.$router.push({name:'mainpage'});
        }
    },
    computed: {
        emailErrors() {
        const errors = [];
        if (!this.$v.EmailAddress.$dirty) return errors;
        !this.$v.EmailAddress.email && errors.push("Must be valid e-mail");
        !this.$v.EmailAddress.required && errors.push("E-mail is required");
        return errors;
        },
        passwordErrors() {
        const errors = [];
        if (!this.$v.password.$dirty) return errors;
        !this.$v.password.required && errors.push("Password is required");
        return errors;
        }
    },
}
</script>

<style scoped>
.auth-card {
    animation: auth-card-in 360ms ease both;
}

.v-text-field--outlined >>> fieldset {
    border-color: rgba(0, 0, 0, 0.15);
}

.v-text-field--outlined.v-input--is-focused >>> fieldset {
    border-color: #FF9062 !important;
}

@keyframes auth-card-in {
    from {
        opacity: 0;
        transform: translateY(16px);
    }
    to {
        opacity: 1;
        transform: translateY(0);
    }
}

</style>
