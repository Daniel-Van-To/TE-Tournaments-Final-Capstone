<template>
    <!-- "Reminder: ?. means that if the resulting 'chained' value (next property, ie 'message' is chained after notification) is nullish (null or undefined), the statement will evaluate to undefined
        example here: if notification hasn't been set or has timed out, message may be null. using ?. here makes this evaluate correctly." -->
    <div id="xxholderxx">
        <div v-bind:class="notificationClass" v-show="notification" v-on:click="clearNotification">
            {{ notification?.message }}
        </div>
    </div>
</template>

<script>
export default {
    computed: {
        notification() {
            return this.$store.state.notification;
        },
        notificationClass() {
            return {
                'status-message': true,
                error: this.notification?.type?.toLowerCase() === 'error',
                success: this.notification?.type?.toLowerCase() === 'success'
            };
        }
    },
    methods: {
        clearNotification() {
            this.$store.commit('CLEAR_NOTIFICATION');
        },
    }
};

</script>


<style scoped>

#holder {
    display: flex;
    flex-grow: 1;
    width:1vw;
}
.status-message {
    display: block;
    border-radius: 5px;
    font-weight: bold;
    font-size: 1rem;
    text-align: center;
    padding: 10px;
    margin-bottom: 10px;
    cursor: pointer;
    /* width: fit-content; */
    align-self: center;
}

.status-message.success {
    background-color: #90ee90;
}

.status-message.error {
    background-color: #f08080;
}
</style>