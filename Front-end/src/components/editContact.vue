<template>
  <div>
    <v-menu
      v-model="menu"
      :close-on-content-click="false"
      :nudge-width="300"
      persistent
      
    >
      <template v-slot:activator="{ on, attrs }">
        <v-btn
            class="mx-1 contact-icon-btn"
            icon
            color="primary"
            v-bind="attrs" 
            v-on="on"
        >
            <v-icon>mdi-account-edit-outline</v-icon>
        </v-btn>

      </template>

      <v-card class="menu-card">
        <v-card-title class="menu-title">
          <v-icon color="primary" class="mr-2">mdi-account-edit-outline</v-icon>
          Edit Contact
        </v-card-title>
        <v-list>
          <v-list-item>
            <v-col>
                <h2>Name</h2>
                <v-text-field
                    v-model="newContact.name"
                    outlined
                    dense
                    rounded
                    required
                ></v-text-field>
            </v-col>
          </v-list-item>

            <v-divider></v-divider>

          <v-list-item v-for="(email,i) in newContact.emailAddresses"
          :key = i>
            <v-col>
                <h2>E-mail no. {{i+1}}</h2>
                <v-text-field
                    v-model="newContact.emailAddresses[i]"
                    outlined
                    dense
                    rounded
                ></v-text-field>
            </v-col>
          </v-list-item>
          

        </v-list>

        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn text class="ms-action-btn" @click="menu = false">Cancel</v-btn>
          <v-btn color="primary" dark class="ms-action-btn" @click="edit()">Edit</v-btn>
        </v-card-actions>

      </v-card>
    </v-menu>
  </div>
</template>

<script>
  export default {
    props :['Oldcontact','Oldname'],
    data: function (){
        return {
            menu: false,
            newContact : "to" ,
        }
    },
    mounted () {
        this.newContact = JSON.parse(JSON.stringify(this.Oldcontact));
    },
    methods : {
      edit(){
        this.menu = false ;
        this.$emit('editContact',[this.Oldcontact.name,this.newContact]);
      }
    }
  }
</script>
<style scoped>
.contact-icon-btn {
  background: rgba(255, 144, 98, 0.1);
}

.menu-card {
  border-radius: 16px;
  overflow: hidden;
}

.menu-title {
  background: rgba(255, 144, 98, 0.08);
  color: var(--ms-text);
  font-weight: 700;
}

h2 {
  color: var(--ms-text);
  font-size: 0.88rem;
  font-weight: 700;
  margin-bottom: 8px;
}
</style>
