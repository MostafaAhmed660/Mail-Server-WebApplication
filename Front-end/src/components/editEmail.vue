<template>
  <div>
    <v-menu
      v-model="menu"
      :close-on-content-click="false"
      :nudge-width="300"
      
    >
      <template v-slot:activator="{ on, attrs }">
        <v-tooltip bottom>
          <template v-slot:activator="{ on: tipOn }">
            <span v-on="tipOn">
        <v-btn
          class="email-icon-btn"
          icon
          small
          color="primary"
          v-bind="attrs" 
          v-on="on"
        >
        <v-icon>
          mdi-pencil
        </v-icon>
    </v-btn>
            </span>
          </template>
          <span>Edit email</span>
        </v-tooltip>
      </template>

      <v-card class="menu-card">
        <v-card-title class="menu-title">
          <v-icon color="primary" class="mr-2">mdi-pencil</v-icon>
          Edit Email
        </v-card-title>
        <v-list>
          <v-list-item>
            <v-col>
                <h2>E-mail</h2>
                <v-text-field
                    v-model="newEmail"
                    placeholder="Enter New Email Here"
                    outlined
                    dense
                    rounded
                    required
                ></v-text-field>
            </v-col>
          </v-list-item>
        </v-list>

        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn text class="ms-action-btn" @click="menu = false">Cancel</v-btn>
          <v-btn color="primary" dark class="ms-action-btn" @click="change()">Save</v-btn>
        </v-card-actions>

      </v-card>
    </v-menu>
  </div>
</template>

<script>
  export default {
    props : ["i","j"],
    data: () => ({
      menu: false,
      newEmail : ''
    }),
    methods : {
      change(){
        this.$emit('editEmail',[this.i,this.j,this.newEmail]);
      }
    }
  }
</script>
<style scoped>
.email-icon-btn {
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
