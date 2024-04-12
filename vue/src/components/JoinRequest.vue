<template>
    <tbody>
        <tr>
            <td>{{ request.requesterId }}</td>
            <td><button class="acceptBtn">Accept</button></td>
            <td><button class="denyBtn">Deny</button></td>
        </tr>
    </tbody>
</template>

<script>

export default {
    props: ['columns', 'request'],

}


</script>

<style scoped>


</style>