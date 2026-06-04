<template>
    <v-container class="mail-detail py-8 px-6" fluid>
        <v-card class="mail-detail-card mx-auto" max-width="1040" flat>
            <v-card-title class="detail-title">
                <div>
                    <span class="detail-kicker">Email message</span>
                    <h2>{{mail.subject}}</h2>
                </div>
                <v-spacer></v-spacer>
                <v-chip :color="importanceColor(mail.importance)" text-color="white">
                    {{mail.importance=="veryImportant" ? 'Very Important' : mail.importance}}
                </v-chip>
            </v-card-title>

            <v-divider></v-divider>

            <v-card-text>
                <v-row>
                    <v-col cols="12" md="6">
                        <div class="meta-label">From</div>
                        <v-chip class="meta-chip" color="rgba(255,144,98,0.12)" text-color="#2d3142">
                            <v-icon left small color="primary">mdi-account-arrow-right-outline</v-icon>
                            {{mail.sender}}
                        </v-chip>
                    </v-col>
                    <v-col cols="12" md="6">
                        <div class="meta-label">To</div>
                        <v-chip class="meta-chip" color="rgba(45,49,66,0.08)" text-color="#2d3142">
                            <v-icon left small color="secondary">mdi-account-arrow-left-outline</v-icon>
                            {{mail.recievers[0]}}
                        </v-chip>
                    </v-col>
                    <v-col cols="12">
                        <div class="body-card">
                            {{mail.body}}
                        </div>
                    </v-col>
                </v-row>
            </v-card-text>

            <v-card-actions class="detail-footer">
                <v-icon small color="primary" class="mr-2">mdi-calendar-clock</v-icon>
                <span>{{mail.date}}</span>
            </v-card-actions>
        </v-card>
    </v-container>
</template>


<script>
export default {
    props: ['mail'],
    data: () => ({
    }),
    
    methods: {
        importanceColor(importance) {
            if (importance === 'veryImportant') return '#EF5350';
            if (importance === 'Important') return '#FFA726';
            if (importance === 'medium') return '#5C6BC0';
            return '#4CAF50';
        }
    }
}
</script>

<style scoped>
.mail-detail {
    background: linear-gradient(135deg, #f8fafc 0%, #edf1f7 100%);
    min-height: calc(100vh - 48px);
}

.mail-detail-card {
    background: rgba(255, 255, 255, 0.94);
    border: 1px solid rgba(45, 49, 66, 0.1);
    border-radius: 18px;
    box-shadow: 0 18px 44px rgba(45, 49, 66, 0.12);
    overflow: hidden;
}

.detail-title {
    align-items: flex-start;
    padding: 28px 32px;
}

.detail-kicker {
    color: var(--ms-primary);
    display: block;
    font-size: 0.8rem;
    font-weight: 700;
    letter-spacing: 0.08em;
    margin-bottom: 6px;
    text-transform: uppercase;
}

.detail-title h2 {
    color: var(--ms-text);
    font-size: 2rem;
    font-weight: 700;
    line-height: 1.2;
    margin: 0;
}

.meta-label {
    color: var(--ms-muted);
    font-size: 0.8rem;
    font-weight: 700;
    letter-spacing: 0.08em;
    margin-bottom: 10px;
    text-transform: uppercase;
}

.meta-chip {
    border-radius: 999px;
    max-width: 100%;
}

.body-card {
    background: #fbfcfe;
    border: 1px solid rgba(45, 49, 66, 0.08);
    border-radius: 16px;
    color: var(--ms-text);
    font-size: 1rem;
    line-height: 1.7;
    min-height: 260px;
    padding: 24px;
    white-space: pre-wrap;
}

.detail-footer {
    background: rgba(45, 49, 66, 0.03);
    color: var(--ms-muted);
    font-weight: 600;
    padding: 18px 32px;
}
</style>
