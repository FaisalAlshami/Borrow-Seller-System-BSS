import { createStore } from 'vuex'

const store = createStore({
    state: {
      isCollapse: true,
      userInfo: {
        name:'',
       
      }
    },
    mutations: {
      getUserInfo (state, name) {
        state.userInfo.name = name
      },
      changeisCollapse(state, val){
          console.log("vuex mutations val state.isCollapse:"+val)
        state.isCollapse=val
      }
    },
    actions: {
      asyncGetUserInfo ({ commit }) {
        setTimeout(() => {
          commit("getUserInfo", +new Date() + 'action')
        },2000)
      }
    },
    getters: {
      userInfoGetter (state) {
        return state.userInfo.name
      }
    }
  })

  export default store;