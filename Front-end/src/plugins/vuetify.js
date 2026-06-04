import Vue from 'vue';
import Vuetify from 'vuetify/lib/framework';

Vue.use(Vuetify);

export default new Vuetify({
  theme: {
    dark: false,
    themes: {
      light: {
        primary: '#FF9062',
        secondary: '#2d3142',
        accent: '#FFB199',
        error: '#EF5350',
        success: '#4CAF50',
        warning: '#FFA726',
        info: '#5C6BC0',
      },
    },
  },
});
