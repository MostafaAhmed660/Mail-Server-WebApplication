<template>
    <v-container class="mail-list-page py-8 px-6" fluid>
        <section class="ms-section-header">
          <span class="ms-section-icon">
            <v-icon color="white" size="30">{{ folderIcon }}</v-icon>
          </span>
          <h1 class="ms-section-title">{{folder}}</h1>
        </section>

        <v-card class="toolbar-card ms-card mb-5" flat>
          <v-row align="center" no-gutters>
            <v-btn class="ms-action-btn mr-3" color="white" elevation="0" @click="hidden = !hidden">
              <v-icon left>{{ hidden ? 'mdi-chevron-right' : 'mdi-chevron-left' }}</v-icon>
              Sort
            </v-btn>
            <v-expand-x-transition>
              <v-row v-show="!hidden" class="toolbar-inline" align="center" no-gutters>
                <v-combobox
                  v-model="sort"
                  :items="items"
                  label="Sort by"
                  outlined
                  dense
                  hide-details
                  class="toolbar-field"
                ></v-combobox>
                <v-btn class="ms-action-btn ml-2" color="secondary" dark @click="applySort">
                  <v-icon left>mdi-check</v-icon>
                  Apply
                </v-btn>
              </v-row>
            </v-expand-x-transition>

            <v-divider vertical class="mx-4 hidden-sm-and-down"></v-divider>

            <v-btn class="ms-action-btn mr-3" color="white" elevation="0" @click="hidden2 = !hidden2">
              <v-icon left>{{ hidden2 ? 'mdi-chevron-right' : 'mdi-chevron-left' }}</v-icon>
              Filter
            </v-btn>
            <v-expand-x-transition>
              <v-row v-show="!hidden2" class="toolbar-inline" align="center" no-gutters>
                <v-combobox
                  v-model="filter"
                  :items="items"
                  label="Filter by"
                  outlined
                  dense
                  hide-details
                  class="toolbar-field"
                ></v-combobox>
                <v-text-field
                  v-show="filter!='importance'"
                  clearable
                  dense
                  outlined
                  hide-details
                  label="Keyword"
                  v-model="filtername"
                  class="toolbar-field ml-2"
                ></v-text-field>
                <v-slider
                  class="importance-slider ml-4"
                  v-show="filter=='importance'"
                  v-model="importance"
                  :tick-labels="importanceList"
                  :max="3"
                  step="1"
                  ticks="always"
                  tick-size="4"
                  hide-details
                  color="primary"
                  track-color="rgba(45, 49, 66, 0.16)"
                ></v-slider>
                <v-btn class="ms-action-btn ml-2" color="primary" dark @click="applyFilter">
                  <v-icon left>mdi-filter-check-outline</v-icon>
                  Apply
                </v-btn>
              </v-row>
            </v-expand-x-transition>
          </v-row>
        </v-card>

        <v-row>
          <v-col cols="12">
            <v-dialog
                v-model="dialog"
                fullscreen
                hide-overlay
                transition="dialog-bottom-transition"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-list class="email-list" three-line>
                          <v-list-item v-for="(messeage,i) in messeages"
                          :key=i
                          class="email-card"
                          @click="openMessage(i)"
                          v-bind="attrs"
                          v-on="on"
                          >
                            <v-list-item-avatar class="mail-avatar">
                              <v-img src="../assets/mail.png"></v-img>
                            </v-list-item-avatar>

                            <v-list-item-content>
                              <v-list-item-title class="sender-title">From: {{ messeage.sender }}</v-list-item-title>

                              <v-list-item-subtitle class="email-subtitle">
                                To: {{messeage.recievers[0]}}
                              </v-list-item-subtitle>

                              <v-list-item-subtitle class="email-subject">
                                {{messeage.subject}}
                              </v-list-item-subtitle>
                              
                              <v-list-item-subtitle class="email-body">
                                {{messeage.body}}
                              </v-list-item-subtitle>
                            </v-list-item-content>
                            <div class="email-meta">
                              <v-chip small color="rgba(45,49,66,0.08)" text-color="#2d3142">
                                <v-icon left small>mdi-calendar-clock</v-icon>
                                {{ messeage.date }}
                              </v-chip>
                              <v-chip small :color="importanceColor(messeage.importance)" text-color="white">
                                {{ messeage.importance=="veryImportant" ? 'Very Important' : messeage.importance }}
                              </v-chip>
                            </div>

                            <v-btn
                              class="email-action"
                              @click.stop="deleteORretrieve(messeage)"
                              icon
                              :color="delete_retrieve=='delete' ? 'error' : 'success'"
                            ><v-icon>{{ delete_retrieve=="delete" ? 'mdi-delete-outline' : 'mdi-delete-restore' }}</v-icon>
                            </v-btn>
                          </v-list-item>
                  
                    </v-list>

        </template>

                <v-card>
                        <v-toolbar
                        color="#2d3142"
                        dark
                        dense
                        flat
                        >
                          <v-btn
                              icon
                              dark
                              @click="dialog = false"
                          >
                              <v-icon>mdi-close</v-icon>
                          </v-btn>
                        <v-toolbar-title class="dialog-title"><v-icon left>mdi-mail</v-icon>MailSphere</v-toolbar-title>
                        <v-spacer></v-spacer>
                        </v-toolbar>

                        <ViewMail :mail="mail" @reply= "reply($event)" ></ViewMail>

                </v-card>
          </v-dialog>
          </v-col>
        </v-row>

        <div class="text-center mt-6">
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

import ViewMail from './viewMail.vue'

import Vue from 'vue'
import axios from 'axios'
import VueAxios from 'vue-axios'


export default {
    props: ['messeages','folder','numberOfPages'],
    data: function (){
      return {
        dialog: false,
        hidden: true,
        hidden2: true,
        disable: false,
        page: 1,
        n:"",
        indexFolder : '' ,
        sort: 'null',
        filter: 'null',
        filtername : 'null',
        items: ['null','subject','sender','SenderAddress','date','importance'],
        mail:"",
        delete_retrieve : "delete",
        importanceList: ['1','2','3','4'],
        importanceList2: ['low','medium','Important','veryImportant'],
        importance: '',
      }
    },
    mounted () {
        console.log(this.f);
        console.log(this.messeages);
        if(this.folder == "Inbox"){
          this.indexFolder = 0 ;
          this.delete_retrieve = "delete";
        }
        else if(this.folder == "Sent"){
          this.indexFolder = 4;
          this.delete_retrieve = "delete";

        }
        else if(this.folder == "Trash"){
          this.indexFolder = 2;
          this.delete_retrieve = "retrieve";
        }
        else {
          this.indexFolder = 3;
          this.delete_retrieve = "delete";
        }
    },
    components : {
      ViewMail
    },
    computed: {
      folderIcon() {
        if (this.folder === "Inbox") return "mdi-inbox-arrow-down";
        if (this.folder === "Sent") return "mdi-send";
        if (this.folder === "Trash") return "mdi-trash-can-outline";
        return "mdi-file-outline";
      }
    },
    methods : {
        importanceColor(importance) {
          if (importance === 'veryImportant') return '#EF5350';
          if (importance === 'Important') return '#FFA726';
          if (importance === 'medium') return '#5C6BC0';
          return '#4CAF50';
        },
        deleteORretrieve(messeage) {
          console.log(messeage);
          console.log(this.indexFolder);
          if (this.indexFolder === 2) {
            axios.get('http://localhost:8080/api/retriveFromTrash',{
                params: {
                    id : messeage.id,
                }
            }).then(Response=>{
                // axios.get('http://localhost:8080/api/getPage',{
                // params: {
                //     PageNumber : this.page,
                // }
                // }).then(Response=>{
                //   const Data = Response.data;
                //   this.messeages = Data ;
                // });
            });
          }
          else {
            axios.get('http://localhost:8080/api/bulkDelete',{
                  params: {
                      id : messeage.id,
                      indexOfDefaultFolder : this.indexFolder
                  }
              }).then(Response=>{
                // axios.get('http://localhost:8080/api/getPage',{
                // params: {
                //     PageNumber : this.page,
                // }
                // }).then(Response=>{
                //   const Data = Response.data;
                //   this.messeages = Data ;
                // });
              });
          }
          this.refresh(this.indexFolder);
        },
        refresh(index) {
          this.$emit('refresh',index);
        },
        openMessage(n) {
          console.log("ok i will show this mess" + n)
          this.mail = this.messeages[n];
        },
         
        changeThePage(){
          console.log("okokkoko")
          axios.get('http://localhost:8080/api/getPage',{
                params: {
                    PageNumber : this.page
                }
          }).then(Response=>{
                const Data = Response.data;
                this.messeages = Data ;
          });
        },
        
        applySort(){
          console.log(this.sort);
          this.sortandFilter();
        },

        applyFilter(){
            if (this.filter === 'importance'){
              this.filtername = this.importanceList2[this.importance];
              console.log(this.filtername);
            }
          this.sortandFilter();
          console.log(this.filter);
          
        },

        sortandFilter : function(){
          axios.post('http://localhost:8080/api/FilterAndSort?sort='+this.sort,
                [ [ this.filter , this.filtername ] ]
          ).then(Response=>{
                const Data = Response.data;
                this.messeages = Data ;
          });
        },
    }
}
</script>

<style scoped>
.toolbar-card {
  padding: 16px;
}

.toolbar-inline {
  flex: 0 1 auto;
}

.toolbar-field {
  max-width: 210px;
}

.importance-slider {
  max-width: 260px;
  min-width: 220px;
}

.email-list {
  background: transparent;
  padding: 0;
}

.email-card {
  background: rgba(255, 255, 255, 0.94);
  border: 1px solid rgba(45, 49, 66, 0.1);
  border-left: 5px solid var(--ms-primary);
  border-radius: 16px;
  box-shadow: 0 10px 28px rgba(45, 49, 66, 0.08);
  margin-bottom: 14px;
  min-height: 118px;
  padding: 14px 18px;
  transition: box-shadow 160ms ease, transform 160ms ease, border-color 160ms ease;
}

.email-card:hover {
  border-color: rgba(255, 144, 98, 0.34);
  box-shadow: 0 16px 38px rgba(45, 49, 66, 0.14);
  transform: translateY(-2px);
}

.mail-avatar {
  background: rgba(255, 144, 98, 0.1);
  border-radius: 14px;
  padding: 8px;
}

.sender-title {
  color: var(--ms-text);
  font-size: 1rem;
  font-weight: 700;
}

.email-subtitle {
  color: var(--ms-muted) !important;
  font-size: 0.86rem;
}

.email-subject {
  color: var(--ms-text) !important;
  font-weight: 600;
}

.email-body {
  color: var(--ms-muted) !important;
  max-width: 760px;
}

.email-meta {
  align-items: flex-end;
  display: flex;
  flex-direction: column;
  gap: 8px;
  min-width: 190px;
}

.email-action {
  margin-left: 12px;
}

.dialog-title {
  align-items: center;
  display: flex;
  font-weight: 700;
}

@media (max-width: 900px) {
  .toolbar-inline {
    flex-basis: 100%;
    margin-top: 12px;
  }

  .email-card {
    align-items: flex-start;
    flex-wrap: wrap;
  }

  .email-meta {
    align-items: flex-start;
    margin-left: 72px;
    min-width: 100%;
  }
}
</style>
