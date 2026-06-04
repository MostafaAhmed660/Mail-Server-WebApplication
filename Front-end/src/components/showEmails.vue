<template>
    <v-container class="py-8 px-6" fluid>
        <section class="ms-section-header">
          <span class="ms-section-icon">
            <v-icon color="white" size="30">mdi-email-multiple-outline</v-icon>
          </span>
          <h1 class="ms-section-title">{{tittle}}</h1>
        </section>
        <v-row>
          <v-col cols="12">
          <v-list class="simple-email-list" three-line>
                  <v-list-item v-for="(messeage,i) in messeages"
                  :key=i
                  class="simple-email-card"
                  @click="show(i)"
                  >
                    
                    <v-list-item-avatar class="simple-email-avatar">
                      <v-img src="../assets/mail.png"></v-img>
                    </v-list-item-avatar>

                    <v-list-item-content>
                      <v-list-item-title class="simple-sender">From: {{ messeage.sender }}</v-list-item-title>

                      <v-list-item-subtitle class="simple-subject">
                        {{messeage.subject}}
                      </v-list-item-subtitle>
                      <v-list-item-subtitle class="simple-body">
                        {{messeage.body}}
                      </v-list-item-subtitle>
                    </v-list-item-content>
                  </v-list-item>
          
              </v-list>
          </v-col>
        </v-row>
      </v-container>
</template>

<script>


export default {
    props: ['messeages','tittle'],
    data: function (){
      return{}
    },
    mounted () {
        console.log(this.messeages);

    },
    methods : {
        show : function(i){
          console.log(i);
        }
    }
}
</script>

<style scoped>
.simple-email-list {
  background: transparent;
  padding: 0;
}

.simple-email-card {
  background: rgba(255, 255, 255, 0.94);
  border: 1px solid rgba(45, 49, 66, 0.1);
  border-left: 5px solid var(--ms-primary);
  border-radius: 16px;
  box-shadow: 0 10px 28px rgba(45, 49, 66, 0.08);
  margin-bottom: 14px;
  min-height: 104px;
  padding: 14px 18px;
  transition: box-shadow 160ms ease, transform 160ms ease;
}

.simple-email-card:hover {
  box-shadow: 0 16px 38px rgba(45, 49, 66, 0.14);
  transform: translateY(-2px);
}

.simple-email-avatar {
  background: rgba(255, 144, 98, 0.1);
  border-radius: 14px;
  padding: 8px;
}

.simple-sender {
  color: var(--ms-text);
  font-weight: 700;
}

.simple-subject {
  color: var(--ms-text) !important;
  font-weight: 600;
}

.simple-body {
  color: var(--ms-muted) !important;
}
</style>



