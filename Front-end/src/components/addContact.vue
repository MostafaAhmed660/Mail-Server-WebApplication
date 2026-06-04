<template>
    <v-row justify="center" class="add-contact-wrap">
        <v-dialog
        v-model="dialog"
        max-width="600px"
        >
        <template v-slot:activator="{ on, attrs }">
            <v-btn class="addnewcontact ms-action-btn"
                color="primary"
                elevation="0"
                large
                dark
                v-bind="attrs"
                v-on="on"
            >
              <v-icon left>mdi-account-plus-outline</v-icon>
              Add New Contact
            </v-btn>
        </template>
            <v-card class="add-contact-card">

                <v-card-title class="dialog-header">
                    <v-icon color="primary" class="mr-2">mdi-account-plus-outline</v-icon>
                    <span>{{ currentTitle }}</span>
                </v-card-title>

                <div class="step-dots">
                  <span :class="['step-dot', { active: step === 1 }]"></span>
                  <span :class="['step-dot', { active: step === 2 }]"></span>
                </div>

                <v-window v-model="step">
                
                <v-window-item :value="1">
                    <v-card-text>
                            <v-col cols="12">
                            <v-text-field
                                v-model=newUser.name
                                label="Name of the New Contact*"
                                outlined
                                dense
                                rounded
                                prepend-inner-icon="mdi-account-outline"
                                required
                                ></v-text-field>
                            </v-col>

                            <v-col cols="12">
                                <v-text-field
                                    v-model.number=numberOfEmails
                                    type="number"
                                    label="Number of Emails of this contact*"
                                    outlined
                                    dense
                                    rounded
                                    prepend-inner-icon="mdi-email-multiple-outline"
                                    min="1"
                                    required>
                                </v-text-field>
                            </v-col>
                    </v-card-text>
                </v-window-item>

                <v-window-item :value="2">
                    <v-card-text>
                        <v-col cols="12" v-for="i in numberOfEmails"
                            :key="i">
                                <v-text-field
                                v-model="newUser.emailAddresses[i-1]"
                                label="Email*"
                                outlined
                                dense
                                rounded
                                prepend-inner-icon="mdi-email-outline"
                                required
                                ></v-text-field>
                        </v-col>
                    </v-card-text>
                </v-window-item>
                
                </v-window>

                <v-divider></v-divider>

                <v-card-actions class="dialog-actions">
                <v-btn
                    :disabled="step === 1"
                    text
                    class="ms-action-btn"
                    @click="step--"
                >
                    Back
                </v-btn>
                <v-spacer></v-spacer>
                <v-btn
                    color="primary"
                    dark
                    class="ms-action-btn"
                    @click="nextaction"
                >
                    {{tittleButton}}
                </v-btn>
                </v-card-actions>
            </v-card>
    </v-dialog>
    </v-row>
</template>

<script>
    import Contact from './contact.vue'

    import Vue from 'vue'
    import axios from 'axios'
    import VueAxios from 'vue-axios'

  export default {
    data: function(){
        return {
            step : 1,
            dialog: false,
            numberOfEmails : 1,
            newUser : {name : '' ,emailAddresses : []}
        }
    },
    computed: {
      currentTitle () {
        switch (this.step) {
          case 1: return 'Adding New Contact'
          default: return 'Adding New Contact Emails'
        }
      },
      tittleButton (){
          switch (this.step) {
          case 1: return 'Next'
          default: return 'Add New Contact'
        }
      }
    },
    methods:{
        nextaction : function(){
            this.step++;
            if(this.step === 3){
                this.dialog = false ;
                this.addContact();
            }
        },
        addContact(){
            this.$emit('addContact',this.newUser);
        }
    }
}
</script>

<style scoped>
.add-contact-wrap {
    margin: 0;
}

.addnewcontact{
    box-shadow: 0 10px 24px rgba(255, 144, 98, 0.28);
}

.add-contact-card {
    border-radius: 18px;
    overflow: hidden;
}

.dialog-header {
    background: rgba(255, 144, 98, 0.08);
    color: var(--ms-text);
    font-weight: 700;
    padding: 22px 26px;
}

.step-dots {
    display: flex;
    gap: 8px;
    padding: 18px 26px 0;
}

.step-dot {
    background: rgba(45, 49, 66, 0.16);
    border-radius: 999px;
    height: 8px;
    transition: all 160ms ease;
    width: 26px;
}

.step-dot.active {
    background: var(--ms-primary);
    width: 44px;
}

.dialog-actions {
    padding: 16px 24px 22px;
}
</style>
