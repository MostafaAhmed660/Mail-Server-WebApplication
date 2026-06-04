<template>
  <v-app>
    <transition name="route-fade" mode="out-in">
      <router-view/>
    </transition>
  </v-app>
</template>

<script>

export default {
  name: 'App',

  data: () => ({
  }),
};
</script>

<style>
:root {
  --ms-primary: #FF9062;
  --ms-primary-soft: #FFB199;
  --ms-dark: #2d3142;
  --ms-sidebar: #232636;
  --ms-surface: rgba(255, 255, 255, 0.92);
  --ms-text: #2d3142;
  --ms-muted: #6c7086;
  --ms-success: #4CAF50;
  --ms-error: #EF5350;
  --ms-warning: #FFA726;
  --ms-border: rgba(45, 49, 66, 0.1);
}

* {
  font-family: 'Poppins', sans-serif !important;
}

body {
  background: linear-gradient(135deg, #f8fafc 0%, #eef2f7 100%);
  color: var(--ms-text);
}

.v-application {
  background: transparent !important;
}

::-webkit-scrollbar {
  width: 10px;
  height: 10px;
}

::-webkit-scrollbar-track {
  background: #f2f4f8;
}

::-webkit-scrollbar-thumb {
  background: rgba(45, 49, 66, 0.24);
  border: 2px solid #f2f4f8;
  border-radius: 999px;
}

::-webkit-scrollbar-thumb:hover {
  background: rgba(255, 144, 98, 0.7);
}

.route-fade-enter-active,
.route-fade-leave-active {
  transition: opacity 180ms ease, transform 180ms ease;
}

.route-fade-enter,
.route-fade-leave-to {
  opacity: 0;
  transform: translateY(8px);
}

.ms-section-header {
  align-items: center;
  background: linear-gradient(135deg, var(--ms-dark), #3b4057);
  border-radius: 16px;
  box-shadow: 0 18px 40px rgba(45, 49, 66, 0.18);
  color: white;
  display: flex;
  gap: 16px;
  margin-bottom: 24px;
  min-height: 104px;
  overflow: hidden;
  padding: 24px;
  position: relative;
}

.ms-section-header::after {
  background: rgba(255, 144, 98, 0.16);
  border-radius: 999px;
  content: '';
  height: 150px;
  position: absolute;
  right: -54px;
  top: -58px;
  width: 150px;
}

.ms-section-icon {
  align-items: center;
  background: rgba(255, 255, 255, 0.14);
  border: 1px solid rgba(255, 255, 255, 0.16);
  border-radius: 16px;
  display: flex;
  height: 56px;
  justify-content: center;
  position: relative;
  width: 56px;
  z-index: 1;
}

.ms-section-title {
  font-size: 1.8rem;
  font-weight: 700;
  line-height: 1.1;
  margin: 0;
  position: relative;
  z-index: 1;
}

.ms-card {
  background: var(--ms-surface) !important;
  border: 1px solid var(--ms-border) !important;
  border-radius: 16px !important;
  box-shadow: 0 14px 36px rgba(45, 49, 66, 0.1) !important;
}

.ms-action-btn {
  border-radius: 999px !important;
  letter-spacing: 0 !important;
  text-transform: none !important;
}
</style>
