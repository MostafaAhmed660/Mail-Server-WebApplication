<template>
    <v-container
        class="py-8 px-6"
        fluid
    >
        <v-row>
          <v-col
            cols="12"
          >
            <v-card>

                <v-img
                    height="90px"
                    src="..\assets\maintittle6.jpg"
                >
                <v-card-title class="white--text mt-5">
                    <h2>Sending A New E-Mail</h2>
                </v-card-title>
                </v-img>

                <v-card-text>
                    <v-row>
                      <v-col md="11">
                        <v-row v-for="(reciever,r) in receiverEmailAddress" :key=r>
                            <v-text-field
                                v-model="receiverEmailAddress[r]"
                                label="To*"
                                required
                                prepend-icon="mdi-arrow-top-left-thick"
                                ></v-text-field>
                        </v-row>
                      </v-col>       
                        <v-col >
                            <v-btn
                                icon
                                @click="addReceiver"
                            >
                                <v-icon>mdi-account-plus</v-icon>
                            </v-btn>
                        </v-col>
                    </v-row>

                    <v-col>
                    <v-text-field
                        v-model="subject"
                        label="Subject*"
                        required
                        prepend-icon="mdi-tag-text"
                        ></v-text-field>
                    </v-col>

                    <v-col>
                    <v-textarea
                        filled
                        name="input-7-4"
                        label="write your body of your email here"
                        v-model="body"
                        
                    ></v-textarea>
                    </v-col>

                       <v-row>
                    <v-col md="6">
                    <v-file-input
                       v-model="file"
                       @click="addToAttachmentList(file)"
                       hide-input
                        truncate-length="100"
                        label="Adding Attachment"
                    >Add attachment
                    </v-file-input>
                    </v-col>
                 
                    <v-col  >
                        <span class="subheading">Mail Importance</span>
                        <v-card-text>
                            <v-slider
                                v-model="importance"
                                :tick-labels="importanceList"
                                :max="3"
                                step="1"
                                ticks="always"
                                tick-size="4"
                            ></v-slider>
                        </v-card-text>
                    </v-col>
                    </v-row>


                    <!-- <v-col v-for="i in attachments"
                    :key="i">
                    <v-list>
                        <v-list-item>{{i}}</v-list-item>
                    </v-list>
                    </v-col> -->


                    <v-divider class="mt-12"></v-divider>

                    <v-card-actions>
                        <v-btn text
                        @click="reset">
                            Reset E-Mail
                        </v-btn>


                        <v-spacer></v-spacer>
                        <v-btn text>
                            Cancel
                        </v-btn>
                    
                    <v-btn
                        color="primary"
                        text
                        @click="send"
                    >
                        Send
                    </v-btn>

                    </v-card-actions>

                    <v-snackbar
                      v-model="snackbar"
                    >
                      {{ text }}

                      <template v-slot:action="{ attrs }">
                        <v-btn
                          color="blue"
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

<style>

</style>