<template>
  <v-card class="mail-shell" flat>
    <v-toolbar class="mail-topbar" flat dark height="112">
      <v-toolbar-title class="mail-topbar-title">
        <span class="topbar-icon">
          <v-icon color="white" size="32">mdi-email-outline</v-icon>
        </span>
        <span>
          <span class="welcome-label">Welcome back</span>
          <h1>Welcome, {{username}}</h1>
        </span>
      </v-toolbar-title>
    </v-toolbar>

    <v-tabs vertical dark v-model='tab' class="mail-tabs">
        <v-sheet dark class="sidebar-profile">
          <v-avatar class="profile-avatar" size="68">
            <img src="../assets/user.png" alt="user_photo" />
          </v-avatar>
          <div class="profile-email">{{emailAddress}}</div>
        </v-sheet>

        <v-tab class="nav-tab" href='#sendNewEmail'>
            <v-icon left> mdi-email-plus-outline </v-icon>
            Send New Email
        </v-tab>

        <v-tab class="nav-tab" href='#inbox' @click="getInboxEmails">
            <v-icon left>mdi-inbox-arrow-down</v-icon>
            Inbox
        </v-tab>

        <v-tab class="nav-tab" href='#sent' @click="getSentEmails">
            <v-icon left>mdi-send</v-icon>
            Sent
        </v-tab>

        <v-tab class="nav-tab" href='#trash' @click="getTrashEmails">
            <v-icon left>mdi-trash-can-outline</v-icon>
            Trash
        </v-tab>

        <v-tab class="nav-tab" href='#draft' @click="getDraftEmails">
            <v-icon left>mdi-file-outline</v-icon>
            Draft
        </v-tab>

        <v-tab class="nav-tab" href='#contacts' @click="getContacts">
            <v-icon left>mdi-contacts</v-icon>
            Contacts
        </v-tab>

        <v-tab class="nav-tab logout-tab" @click="logout">
            <v-icon left>mdi-logout-variant</v-icon>
            Log-Out
        </v-tab>
        
        
        <!-- this for send new email -->
        <v-tab-item class="mail-content" value='sendNewEmail'>
            <SendNewEmail :senderEmailAddress="emailAddress"/>
        </v-tab-item>

        <!-- this for inbox -->
        <v-tab-item class="mail-content" value='inbox'>
            <ShowEmails :messeages = "inboxMails" folder = "Inbox" :numberOfPages= "inboxNumPages" @refresh = "refresh($event)"></ShowEmails>
        </v-tab-item>

        <!-- this for send -->
        <v-tab-item class="mail-content" value='sent'>
            <ShowEmails :messeages = "sentMails" folder = "Sent" :numberOfPages= "sentNumPages" @refresh = "refresh($event)"></ShowEmails>
        </v-tab-item>

        <!-- this for trash -->
        <v-tab-item class="mail-content" value='trash'>
            <ShowEmails :messeages = "trashMails" folder = "Trash" :numberOfPages= "trashNumPages" @refresh = "refresh($event)"></ShowEmails>
        </v-tab-item>

        <!-- this for draft -->
        <v-tab-item class="mail-content" value="draft">
            <ShowEmails :messeages = "DraftMails"  folder = "Draft"  :numberOfPages= "DraftNumPages" @refresh = "refresh($event)"></ShowEmails>
        </v-tab-item>

        <!-- this for contacts -->
        <v-tab-item class="mail-content" value="contacts">
            <contact :contacts = "contacts" :numberOfPages= "contactsNumPages"></contact>
        </v-tab-item>


        
    </v-tabs>
  </v-card>
</template>

<script>

//import our component
import Contact from '../components/contact.vue'
import SendNewEmail from '../components/sendNewEmail.vue'
import ShowEmails from '../components/veiwMails.vue'

import Vue from 'vue'
import axios from 'axios'
import VueAxios from 'vue-axios'


export default {
    data: () => ({
        tab : "sendNewEmail",
        username : '',
        emailAddress : '',
        inboxMails : [],
        inboxNumPages : 0 ,
        sentMails : [],
        sentNumPages : 0 ,
        trashMails : [],
        trashNumPages : 0 ,
        DraftMails : [],
        DraftNumPages : 0 ,
        contacts : [],
        contactsNumPages : 0 ,
    }),
    components : {
        Contact,
        SendNewEmail,
        ShowEmails,
    },
    mounted () {
        axios.get('http://localhost:8080/api/getUser',{
        }).then(Response=>{
            const Data = Response.data;
            this.username = Data ;
        });
        axios.get('http://localhost:8080/api/getUseraddress',{
        }).then(Response=>{
            const Data = Response.data;
            this.emailAddress = Data ;
        });
    },
    methods : {
        getInboxEmails : function(){
            axios.get('http://localhost:8080/api/SetEmailsToShow',{
                params: {
                    FolderIndex : 0
                }
            }).then(Response=>{
                const Data = Response.data;
                this.inboxMails = Data ;
                axios.get('http://localhost:8080/api/EmailsNumberOfPages',{
                    }).then(Response=>{
                        const Data = Response.data;
                        this.inboxNumPages = Data ;
                });
            });  
        },
        getSentEmails : function(){
            axios.get('http://localhost:8080/api/SetEmailsToShow',{
                params: {
                    FolderIndex : 4
                }
            }).then(Response=>{
                const Data = Response.data;
                this.sentMails = Data ;
                axios.get('http://localhost:8080/api/EmailsNumberOfPages',{
                    }).then(Response=>{
                        const Data = Response.data;
                        this.sentNumPages = Data ;
                });
            });  
        },
        getTrashEmails : function(){
            axios.get('http://localhost:8080/api/SetEmailsToShow',{
                params: {
                    FolderIndex : 2
                }
            }).then(Response=>{
                const Data = Response.data;
                this.trashMails = Data ;
                axios.get('http://localhost:8080/api/EmailsNumberOfPages',{
                    }).then(Response=>{
                        const Data = Response.data;
                        this.trashNumPages = Data ;
                });
            });  
        },
        getDraftEmails : function(){
            axios.get('http://localhost:8080/api/SetEmailsToShow',{
                params: {
                    FolderIndex : 3
                }
            }).then(Response=>{
                const Data = Response.data;
                this.DraftMails = Data ;
                axios.get('http://localhost:8080/api/EmailsNumberOfPages',{
                    }).then(Response=>{
                        const Data = Response.data;
                        this.DraftNumPages = Data ;
                });
            });  
        },
        getContacts : function(){
            axios.get('http://localhost:8080/api/GetContactPage',{
                params: {
                    PageNumber : 1
                }
            }).then(Response=>{
                const Data = Response.data;
                this.contacts = Data ;
                axios.get('http://localhost:8080/api/ContactsNumberOfPages',{
                    }).then(Response=>{
                        const Data = Response.data;
                        this.contactsNumPages = Data ;
                });
            });

        },
        logout : function(){
            axios.get('http://localhost:8080/api/logout',{})
            .then(Response=>{
                this.$router.push({name:'login'});
            });
        },
        sleep(ms) {
        return new Promise(
            resolve => setTimeout(resolve, ms)
        );
        },
        async refresh(indexFolder) {
            await this.sleep(60);
            if(indexFolder == 0){
                this.getInboxEmails() ;
                console.log(indexFolder);
            }
            else if(indexFolder == 4){
                this.getSentEmails();
                console.log(indexFolder);
            }
            else if(indexFolder == 2){
                this.getTrashEmails();
                console.log(indexFolder);
            }
            else {
                this.getDraftEmails();
                console.log(indexFolder);
            }
        },
    }
}
</script>

<style scoped>
.mail-shell {
    background: linear-gradient(135deg, #f8fafc 0%, #edf1f7 100%);
    border-radius: 0;
    min-height: 100vh;
    overflow: hidden;
}

.mail-topbar {
    animation: topbar-in 360ms ease both;
    background: linear-gradient(135deg, #2d3142 0%, #353a50 58%, #ff9062 140%) !important;
    border-bottom: 1px solid rgba(255, 255, 255, 0.08);
    box-shadow: 0 18px 44px rgba(45, 49, 66, 0.24);
    padding-left: 28px;
}

.mail-topbar-title {
    align-items: center;
    display: flex;
    gap: 16px;
}

.topbar-icon {
    align-items: center;
    background: rgba(255, 255, 255, 0.12);
    border: 1px solid rgba(255, 255, 255, 0.18);
    border-radius: 18px;
    display: flex;
    height: 58px;
    justify-content: center;
    width: 58px;
}

.welcome-label {
    color: rgba(255, 255, 255, 0.7);
    display: block;
    font-size: 0.82rem;
    font-weight: 600;
    letter-spacing: 0.08em;
    margin-bottom: 4px;
    text-transform: uppercase;
}

.mail-topbar h1 {
    color: white;
    font-size: 2rem;
    font-weight: 700;
    line-height: 1.1;
    margin: 0;
    text-shadow: 0 8px 28px rgba(255, 144, 98, 0.26);
}

.mail-tabs {
    background: transparent;
}

.mail-tabs >>> .v-slide-group {
    background: var(--ms-sidebar);
    border-right: 1px solid rgba(255, 255, 255, 0.08);
    min-height: calc(100vh - 112px);
    padding: 18px 12px;
    width: 280px;
}

.mail-tabs >>> .v-tabs-bar {
    background: var(--ms-sidebar) !important;
}

.sidebar-profile {
    background: transparent !important;
    padding: 18px 12px 22px;
    text-align: center;
}

.profile-avatar {
    border: 3px solid rgba(255, 144, 98, 0.75);
    box-shadow: 0 10px 26px rgba(0, 0, 0, 0.28);
    margin-bottom: 14px;
}

.profile-email {
    color: rgba(255, 255, 255, 0.78);
    font-size: 0.88rem;
    font-weight: 500;
    overflow-wrap: anywhere;
}

.nav-tab {
    border-radius: 14px;
    color: rgba(255, 255, 255, 0.72) !important;
    justify-content: flex-start;
    letter-spacing: 0;
    margin: 5px 0;
    min-height: 48px;
    overflow: hidden;
    padding: 0 16px !important;
    position: relative;
    text-transform: none;
    transition: background 160ms ease, color 160ms ease, transform 160ms ease;
}

.nav-tab::before {
    background: var(--ms-primary);
    border-radius: 0 999px 999px 0;
    content: '';
    height: 22px;
    left: 0;
    opacity: 0;
    position: absolute;
    transform: scaleY(0.5);
    transition: opacity 160ms ease, transform 160ms ease;
    width: 4px;
}

.nav-tab:hover {
    background: rgba(255, 255, 255, 0.07);
    color: white !important;
    transform: translateX(3px);
}

.nav-tab.v-tab--active {
    background: rgba(255, 144, 98, 0.14);
    color: white !important;
}

.nav-tab.v-tab--active::before {
    opacity: 1;
    transform: scaleY(1);
}

.nav-tab >>> .v-icon {
    background: rgba(255, 255, 255, 0.08);
    border-radius: 10px;
    height: 32px;
    justify-content: center;
    margin-right: 12px;
    transition: background 160ms ease, color 160ms ease;
    width: 32px;
}

.nav-tab.v-tab--active >>> .v-icon {
    background: var(--ms-primary);
    color: white;
}

.logout-tab {
    color: rgba(255, 180, 180, 0.88) !important;
    margin-top: 18px;
}

.logout-tab:hover {
    background: rgba(239, 83, 80, 0.12);
}

.mail-content {
    background: linear-gradient(135deg, #f8fafc 0%, #edf1f7 100%);
    min-height: calc(100vh - 112px);
}

.mail-content >>> .v-window-item {
    transition: opacity 180ms ease, transform 180ms ease;
}

@keyframes topbar-in {
    from {
        opacity: 0;
        transform: translateY(-16px);
    }
    to {
        opacity: 1;
        transform: translateY(0);
    }
}

@media (max-width: 900px) {
    .mail-tabs >>> .v-slide-group {
        width: 220px;
    }

    .mail-topbar h1 {
        font-size: 1.45rem;
    }
}
</style>
