const { createApp, onMounted, ref } = Vue
const { createPinia } = Pinia

const seoulApp = createApp({
	setup() {
		const store = useSeoulStore()
		const addressRef = ref(null)
		
		onMounted(() => {
			store.seoulFindData()
		})
		
		return {
			store,
			addressRef
		}
	}
})

seoulApp.use(createPinia())
seoulApp.mount("#seoul_find")