<template>
    <tbody>
        <tr>
            <td>{{ request.requesterId }}</td>
            <td><button>Accept</button></td>
            <td><button>Deny</button></td>
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