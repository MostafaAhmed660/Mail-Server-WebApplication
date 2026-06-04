<template>
    <v-container class="contacts-page py-8 px-6" fluid>
        <section class="ms-section-header contacts-header">
          <span class="ms-section-icon">
            <v-icon color="white" size="30">mdi-contacts-outline</v-icon>
          </span>
          <h1 class="ms-section-title">Contacts</h1>
          <v-spacer></v-spacer>
          <AddContact @addContact = "addContact($event)"/>
        </section>
        <v-row>
          <v-row class="search-row" align="center" no-gutters>
            <v-btn
                    class="search-toggle"
                    icon
                    color="primary"
                    @click="hidden = !hidden"
                    >
                          <v-icon>mdi-account-search</v-icon>
                </v-btn>
                <v-col cols="12" md="4">
                <v-fab-transition>
              <v-text-field
                v-show="!hidden"
                clearable
                outlined
                dense
                rounded
                prepend-inner-icon="mdi-magnify"
                v-model="search"
                label="Search for"
                hide-details
              >
              </v-text-field>              
            </v-fab-transition> 
                </v-col>
            <v-fab-transition>
            <v-btn
                class="ms-action-btn ml-3"
                v-show="!hidden"
                dark
                color="secondary"
                @click="searchContact"
                    >
                      <v-icon left>mdi-check</v-icon>
                      Search
            </v-btn>
            </v-fab-transition> 
          </v-row>

        
          <v-col
            v-for="(contact,i) in contacts"
            :key=i
            cols="12"
          >
            <v-card class="contact-card" flat>
              
              <v-subheader class="contact-card-header">
                <v-avatar class="contact-avatar"><img src="../assets/user.png" alt="user_photo" /></v-avatar>
                <span class="contact-name">{{ contact.name }}</span>
                
                <v-spacer></v-spacer>
                <div class="contact-actions">
                  <EditContact :Oldcontact="contact" :Oldname="contact.name" @editContact = "editContact($event)"/>

                  <AddEmailtoContact :contactobject="contact" @addEmailtoContact = "editContact($event)"/>
                  <v-tooltip bottom>
                    <template v-slot:activator="{ on, attrs }">
                  <v-btn
                      icon
                      color="error"
                      :key="i"
                      v-bind="attrs"
                      v-on="on"
                      @click="removeContact(contact.name)"
                  >
                      <v-icon>mdi-account-remove-outline</v-icon>
                  </v-btn>
                    </template>
                    <span>Delete contact</span>
                  </v-tooltip>
                </div>

              </v-subheader>
              <v-divider></v-divider>
              <v-list class="contact-email-list" two-line>
                
                  <v-list-item
                    v-for="(email,j) in contact.emailAddresses"
                    :key=j
                    class="contact-email-row"
                    
                  >
                    <v-list-item-avatar class="contact-mail-avatar">
                      <v-img src="../assets/mail.png"></v-img>
                    </v-list-item-avatar>

                    <v-list-item-content>
                      <v-list-item-title class="email-row-title">Email {{ j+1 }}</v-list-item-title>

                      <v-list-item-subtitle>
                        {{email}}
                      </v-list-item-subtitle>
                    </v-list-item-content>
                    
                    <EditEmail :i= "i" :j= "j" @editEmail = "editEmail($event)"/>


                    <v-btn
                      class="mx-6"
                      icon
                      color="error"
                      @click="removeEmail(i,j)"
                    >
                      <v-icon>mdi-close</v-icon>
                    </v-btn>

                    <v-snackbar
                      v-model="snackbar"
                      timeout="3000"
                      rounded="pill"
                      color="grey darken-3"
                    >
                      {{ text }}

                      <template v-slot:action="{ attrs }">
                        <v-btn
                          color="primary"
                          text
                          v-bind="attrs"
                          @click="snackbar = false"
                        >
                          Close
                        </v-btn>
                      </template>
                    </v-snackbar>

                  </v-list-item>
                
              </v-list>
            </v-card>
          </v-col>
        </v-row>
        <div class="text-center">
          <v-pagination
            color="primary"
            v-model="page"
            :length=numberOfPages
            :disabled="disable"
            @input="changeThePage"
          ></v-pagination>
        </div>
      </v-container>
</template>

<script>
import AddContact from './addContact.vue'
import EditEmail from './editEmail.vue'
import AddEmailtoContact from './addEmailtoContact.vue'
import EditContact from './editContact.vue'



import Vue from 'vue'
import axios from 'axios'
import VueAxios from 'vue-axios'

export default {
    props :['contacts','numberOfPages'],
    data: () => ({
        newContact : {},
        newEmail : '' ,
        text : '',
        snackbar : false ,
        page : 1 ,
        search: '',
        hidden: true,
        disable: ''
    }),
    components : {
        AddContact,
        EditEmail,
        AddEmailtoContact,
        EditContact
    },
    methods : {
        
        removeEmail(i,j){
            var oldname = this.contacts[i].name ,
            //take copy of it
            newContact = JSON.parse(JSON.stringify(this.contacts[i]));
            newContact.emailAddresses.splice(j,1);

            if( newContact.emailAddresses.length == 0){
                this.removeContact(newContact.name);
            }
            else{
              axios.post('http://localhost:8080/api/editContact?oldName=' + newContact.name,{
                name : newContact.name,
                emailAddresses : newContact.emailAddresses
              }).then(Response=>{
                  const Data = Response.data;
                  this.text = Data ;
                  this.snackbar = true ;
                axios.get('http://localhost:8080/api/GetContactPage',{
                params: {
                  //this must be varable global
                    PageNumber : this.page
                }
                }).then(Response1=>{
                const Data1 = Response1.data;
                this.contacts = Data1 ;
              });  
          });

            }
        },

        removeContact(name) {
          axios.get('http://localhost:8080/api/removeContact',{
            params: {
                Name : name
            }
          }).then(Response=>{
            const Data = Response.data;
            this.text = Data ;
            this.snackbar = true ;
            if (this.text == "Contact Successfully Deleted"){
                  this.getNumberOfPages();
                  axios.get('http://localhost:8080/api/GetContactPage',{
                    params: {
                        PageNumber : this.page
                    }
                  }).then(Response1=>{
                    const Data1 = Response1.data;
                    this.contacts = Data1 ;
                  });
              }
          });
        },

        editEmail(n){
          var i = n[0] , j = n[1] , newEmail = n[2] ;
          var oldname = this.contacts[i].name ,
          newContact = JSON.parse(JSON.stringify(this.contacts[i]));
          newContact.emailAddresses[j] = newEmail ;
          
          axios.post('http://localhost:8080/api/editContact?oldName=' + newContact.name,{
            name : newContact.name,
            emailAddresses : newContact.emailAddresses
          }).then(Response=>{
            const Data = Response.data;
            this.text = Data ;
            this.snackbar = true ;
            axios.get('http://localhost:8080/api/GetContactPage',{
                params: {
                  //this must be varable global
                    PageNumber : this.page
                }
            }).then(Response1=>{
                const Data1 = Response1.data;
                this.contacts = Data1 ;
            });  
          });
        },


        addContact(contact){
            axios.post('http://localhost:8080/api/addContact',{
                name : contact.name,
                emailAddresses : contact.emailAddresses
            }).then(Response=>{
              const Data = Response.data;
                this.text = Data ;
                this.snackbar = true ;
                if (this.text == "Successfully added contact"){
                  axios.get('http://localhost:8080/api/GetContactPage',{
                    params: {
                        PageNumber : this.page
                    }
                  }).then(Response1=>{
                    const Data1 = Response1.data;
                    this.contacts = Data1 ;

                  });
                  this.getNumberOfPages();
              }
            });
        },

        editContact(n){
          //n[0] oldname of contact
          //n[1] new contact with new email
          console.log(n);
          axios.post('http://localhost:8080/api/editContact?oldName=' + n[0],{
            name : n[1].name,
            emailAddresses : n[1].emailAddresses
          }).then(Response=>{
            const Data = Response.data;
            this.text = Data ;
            this.snackbar = true ;
            axios.get('http://localhost:8080/api/GetContactPage',{
                params: {
                  //this must be varable global
                    PageNumber : this.page
                }
            }).then(Response1=>{
                const Data1 = Response1.data;
                this.contacts = Data1 ;
            });  
          });
        },

        changeThePage(){
          axios.get('http://localhost:8080/api/GetContactPage',{
            params: {
              //this must be varable global
                PageNumber : this.page
            }
            }).then(Response=>{
              const Data = Response.data;
              this.contacts = Data ;
          });  
        },

        getNumberOfPages(){
          axios.get('http://localhost:8080/api/ContactsNumberOfPages',{
              }).then(Response=>{
                  const Data = Response.data;
                  this.numberOfPages = Data ;
          });
        },
        searchContact() {
          axios.post('http://localhost:8080/api/SearchContact?Name=' + this.search,{
          }).then(Response=>{
                const Data = Response.data;
                this.contacts = [Data] ;
          });
        }
    }
}
</script>

<style scoped>
.contacts-header {
  flex-wrap: wrap;
}

.search-row {
  background: rgba(255, 255, 255, 0.84);
  border: 1px solid rgba(45, 49, 66, 0.08);
  border-radius: 16px;
  margin: 0 0 20px;
  padding: 14px;
  width: 100%;
}

.search-toggle {
  background: rgba(255, 144, 98, 0.12);
  margin-right: 12px;
}

.contact-card {
  background: rgba(255, 255, 255, 0.94);
  border: 1px solid rgba(45, 49, 66, 0.1);
  border-radius: 18px;
  box-shadow: 0 14px 34px rgba(45, 49, 66, 0.1);
  overflow: hidden;
  transition: box-shadow 160ms ease, transform 160ms ease;
}

.contact-card:hover {
  box-shadow: 0 20px 44px rgba(45, 49, 66, 0.14);
  transform: translateY(-2px);
}

.contact-card-header {
  align-items: center;
  min-height: 86px;
  padding: 18px 24px;
}

.contact-avatar {
  border: 3px solid rgba(255, 144, 98, 0.55);
  margin-right: 16px;
}

.contact-name {
  color: var(--ms-text);
  font-size: 1.2rem;
  font-weight: 700;
}

.contact-actions {
  align-items: center;
  display: flex;
  gap: 4px;
}

.contact-email-list {
  background: transparent;
  padding: 10px 16px 16px;
}

.contact-email-row {
  border-radius: 14px;
  margin-top: 6px;
  transition: background 160ms ease;
}

.contact-email-row:hover {
  background: rgba(255, 144, 98, 0.07);
}

.contact-mail-avatar {
  background: rgba(255, 144, 98, 0.09);
  border-radius: 12px;
  padding: 8px;
}

.email-row-title {
  color: var(--ms-text);
  font-weight: 700;
}

@media (max-width: 700px) {
  .contact-card-header {
    align-items: flex-start;
    flex-wrap: wrap;
    gap: 12px;
  }

  .contact-actions {
    width: 100%;
  }
}
</style>
