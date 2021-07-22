<template>
    <v-img
        src="..\assets\maintittle5.jpg" 
        height="710"
    >

    
        <v-card 
            class="mx-auto my-3"
            max-width="1000"
        >
           <v-card-title primary-title 
           class="layout justify-center"
           color="red">
                <h2>{{mail.subject}}</h2>
            </v-card-title>

            <v-divider></v-divider>


            <v-card-text class="justify-center">
                    <v-col>
                        
                        <h2 class="font-italic">From : </h2>
                        <v-card>
                            <v-card-subtitle class="font-italic">
                                <span class="text--primary"> {{mail.sender}} </span>
                            </v-card-subtitle>
                        </v-card>
                           
                    </v-col>
                    <v-col>
                        <h2 class="font-italic">To : </h2>
                        <v-card>
                            <v-card-subtitle class="font-italic">
                                <span class="text--primary"> {{mail.recievers[0]}}</span>
                            </v-card-subtitle>
                        </v-card>      
                    </v-col>
                    <v-col>
                        <v-card min-height="200">
                            <v-card-subtitle class="text--primary"> {{mail.body}} </v-card-subtitle>
                        </v-card>
                    </v-col>
                    <v-col>
                    <div class="font-italic">{{mail.importance=="veryImportant" ? 'Very Important' : mail.importance}} - {{mail.date}}</div>
                    </v-col>
            </v-card-text>
        </v-card>
    </v-img>
</template>


<script>
export default {
    props: ['mail'],
    data: () => ({
    }),
    
    methods: {
        
    }
}
</script>