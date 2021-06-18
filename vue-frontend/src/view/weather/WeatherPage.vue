<template>
    <div>
        <v-container>
            <WeatherForm></WeatherForm>
        </v-container>
    </div>
</template>

<script>
import WeatherForm from "@/components/weather/WeatherForm";
import SearchLocation from "@/components/weather/SearchLocation";

export default {
    components: {SearchLocation, WeatherForm}
}
</script>

<style scoped>

</style>