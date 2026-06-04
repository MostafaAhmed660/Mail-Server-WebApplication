<template>
    <v-container class="compose-page py-8 px-6" fluid>
        <section class="ms-section-header">
            <span class="ms-section-icon">
                <v-icon color="white" size="30">mdi-email-plus-outline</v-icon>
            </span>
            <h1 class="ms-section-title">Compose New Email</h1>
        </section>
        <v-row>
          <v-col
            cols="12"
          >
            <v-card class="compose-card ms-card" flat>
                <v-card-text>
                    <v-row>
                      <v-col md="11">
                        <v-row v-for="(reciever,r) in receiverEmailAddress" :key=r class="receiver-row">
                            <v-text-field
                                v-model="receiverEmailAddress[r]"
                                label="To*"
                                required
                                outlined
                                dense
                                prepend-inner-icon="mdi-account-arrow-left-outline"
                                class="rounded-lg"
                                placeholder="recipient@example.com"
                                ></v-text-field>
                        </v-row>
                      </v-col>       
                        <v-col class="add-recipient-col">
                            <v-tooltip bottom>
                                <template v-slot:activator="{ on, attrs }">
                            <v-btn
                                v-bind="attrs"
                                v-on="on"
                                color="primary"
                                class="add-recipient-btn"
                                icon
                                @click="addReceiver"
                            >
                                <v-icon>mdi-account-plus</v-icon>
                            </v-btn>
                                </template>
                                <span>Add recipient</span>
                            </v-tooltip>
                        </v-col>
                    </v-row>

                    <v-col>
                    <v-text-field
                        v-model="subject"
                        label="Subject*"
                        required
                        outlined
                        dense
                        prepend-inner-icon="mdi-tag-text-outline"
                        class="rounded-lg"
                        ></v-text-field>
                    </v-col>

                    <v-col>
                    <v-textarea
                        outlined
                        name="input-7-4"
                        label="Write your email body"
                        v-model="body"
                        class="rounded-lg compose-body"
                        rows="10"
                        
                    ></v-textarea>
                    </v-col>

                       <v-row>
                    <v-col md="6">
                    <div class="attachment-zone">
                    <v-icon color="primary" size="34">mdi-paperclip</v-icon>
                    <v-file-input
                       v-model="file"
                       @click="addToAttachmentList(file)"
                        truncate-length="100"
                        label="Add attachment"
                        prepend-icon=""
                        outlined
                        dense
                        hide-details
                    >
                    </v-file-input>
                    </div>
                    </v-col>
                 
                    <v-col  >
                        <div class="importance-card">
                        <span class="importance-title">Mail Importance</span>
                            <v-slider
                                v-model="importance"
                                :tick-labels="importanceList"
                                :max="3"
                                step="1"
                                ticks="always"
                                tick-size="4"
                                color="primary"
                                track-color="rgba(45, 49, 66, 0.16)"
                            ></v-slider>
                        </div>
                    </v-col>
                    </v-row>


                    <!-- <v-col v-for="i in attachments"
                    :key="i">
                    <v-list>
                        <v-list-item>{{i}}</v-list-item>
                    </v-list>
                    </v-col> -->


                    <v-divider class="mt-8"></v-divider>

                    <v-card-actions class="compose-actions">
                        <v-btn
                        class="ms-action-btn"
                        outlined
                        color="secondary"
                        @click="reset">
                            <v-icon left>mdi-refresh</v-icon>
                            Reset E-Mail
                        </v-btn>


                        <v-spacer></v-spacer>
                        <v-btn class="ms-action-btn" text color="secondary">
                            Cancel
                        </v-btn>
                    
                    <v-btn
                        color="primary"
                        dark
                        class="ms-action-btn send-btn"
                        @click="send"
                    >
                        <v-icon left>mdi-send</v-icon>
                        Send
                    </v-btn>

                    </v-card-actions>

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

                    
                </v-card-text>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
</template>

<script>

import Vue from 'vue'
import axios from 'axios'
import VueAxios from 'vue-axios'

export default {
    props : ["senderEmailAddress"] ,
    data: () => ({
        snackbar : false ,
        text : "" ,
        receiverEmailAddress : [''] ,
        subject : '',
        body : '',
        attachments : [],
        file : null,
        importanceList: ['low','medium','important','very important'],
        importanceList2: ['low','medium','Important','veryImportant'],
        importance: 1,
    }),
    methods : {
        reset : function(){
            this.receiverEmailAddress = [''] ;
            this.subject = '';
            this.body = '';
            this.attachments = [];
            this.file = null;
        },
        addToAttachmentList : function(newfile){
            this.attachments.push(1);
            this.file = newfile ;
            console.log(this.file);
        },
        send(){
            var dt = new Date;
            var dformat = `${
                (dt.getMonth()+1).toString().padStart(2, '0')}/${
                dt.getDate().toString().padStart(2, '0')}/${
                dt.getFullYear().toString().padStart(4, '0')} ${
                dt.getHours().toString().padStart(2, '0')}:${
                dt.getMinutes().toString().padStart(2, '0')}:${
                dt.getSeconds().toString().padStart(2, '0')}`;
            console.log(this.importanceList2[this.importance])  ;
            axios.post('http://localhost:8080/api/ComposeMail?send=true',{
                id : 0,
                sender : this.senderEmailAddress ,
                recievers : this.receiverEmailAddress ,
                seen : true ,
                date : dformat ,
                importance : this.importanceList2[this.importance] ,
                subject : this.subject ,
                body : this.body,
                attachements : this.attachments ,

            }).then(Response=>{
                const Data = Response.data;
                this.text = Data ;
                this.snackbar = true ;
            });  
        },
        addReceiver() {
            this.receiverEmailAddress.push('');
        }
     }

}
</script>

<style scoped>
.compose-card {
    padding: 10px;
}

.receiver-row {
    padding: 0 12px;
}

.add-recipient-col {
    align-items: flex-start;
    display: flex;
    justify-content: center;
    padding-top: 12px;
}

.add-recipient-btn {
    background: rgba(255, 144, 98, 0.12);
}

.compose-body >>> textarea {
    line-height: 1.6;
}

.attachment-zone {
    align-items: center;
    background: rgba(255, 144, 98, 0.06);
    border: 1px dashed rgba(255, 144, 98, 0.55);
    border-radius: 16px;
    display: flex;
    gap: 14px;
    min-height: 96px;
    padding: 18px;
}

.attachment-zone >>> .v-input {
    flex: 1;
}

.importance-card {
    background: rgba(45, 49, 66, 0.035);
    border: 1px solid rgba(45, 49, 66, 0.08);
    border-radius: 16px;
    min-height: 96px;
    padding: 18px 22px 4px;
}

.importance-title {
    color: var(--ms-text);
    display: block;
    font-weight: 700;
    margin-bottom: 8px;
}

.compose-actions {
    padding: 18px 12px 4px;
}

.send-btn {
    box-shadow: 0 10px 22px rgba(255, 144, 98, 0.28);
}

@media (max-width: 700px) {
    .compose-actions {
        align-items: stretch;
        flex-direction: column;
        gap: 10px;
    }

    .compose-actions .spacer {
        display: none;
    }
}
</style>
