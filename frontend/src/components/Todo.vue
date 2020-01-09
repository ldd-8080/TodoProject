<template>
  <div class="hello">
    <b-card header="오늘 해야 할 일" style="max-width: 40rem; margin: auto; margin-top: 10vh;"
    class="mb-2" border-variant="info" align="left">

      <b-form-group id="to-do-input">
        <b-container fluid>
          <b-row class="my-1">
            <b-col sm="10">
              <b-form-input v-model="newToDoItemRequest.title" type="text" placeholder="새 할 일을 적으세요." @keyup.enter="createTodo"/>
            </b-col>
            <b-col sm="2">
              <b-button variant="outline-primary" @click="createTodo">추가</b-button>
            </b-col>
          </b-row>
        </b-container>
      </b-form-group>

      <b-list-group v-if="toDoItems && toDoItems.length">
        <b-list-group-item class="list-group-item-flex" v-for="(toDoItem, index) of toDoItems" v-bind:data="toDoItem.id" v-bind:key="toDoItem.id" @mouseover="overEvent(index)" @mouseout="outEvent(index)">
          <b-form-checkbox v-model="toDoItem.done" @change="markDone(toDoItem)">
          </b-form-checkbox>
          <span class="item-span" v-if="toDoItem.done" style="text-decoration: line-through; color: #D3D3D3;">
            {{toDoItem.title}}
          </span>
          <span class="item-span" v-else>{{toDoItem.title}}</span>
          <b-button ref="deleteBtn" variant="danger" size="sm" @click="deleteTodo(toDoItem)" hidden>Delete</b-button>
        </b-list-group-item>
      </b-list-group>
    </b-card>
  </div>
</template>

<script>
import axios from 'axios'

let baseUrl = '/todo'

export default {
  name: 'Todo',
  data () {
    return {
      toDoItems: [
        // {id: '1', title: 'first'},
        // {id: '2', title: 'second'}
      ],
      newToDoItemRequest: {}
    }
  },
  created () {
    this.initTodoList()
  },
  methods: {
    initTodoList: function () {
      let vm = this

      axios.get(baseUrl)
        .then(response => {
          vm.toDoItems = response.data.map(d => d)
        })
        .catch(e => {
          console.log('error', e)
        })
    },
    createTodo: function (event) {
      event.preventDefault()

      let vm = this

      axios.post(baseUrl, vm.newToDoItemRequest)
        .then(response => {
          console.log(response)
          vm.initTodoList()
          vm.newToDoItemRequest = {}
        })
        .catch(e => {
          console.log('error', e)
        })
    },
    markDone: function (toDoItem) {
      if (!toDoItem) return

      let vm = this

      toDoItem.done = !toDoItem.done

      axios.put(baseUrl, toDoItem)
        .then(response => {
          vm.initTodoList()
        })
        .catch(e => {
          console.log('error', e)
        })
    },
    overEvent: function (index) {
      this.$refs.deleteBtn[index].hidden = false
    },
    outEvent: function (index) {
      this.$refs.deleteBtn[index].hidden = true
    },
    deleteTodo: function (toDoItem) {
      console.log(toDoItem)
      if (!confirm('삭제?')) return

      let vm = this

      axios.delete(baseUrl, {data: toDoItem})
        .then(response => {
          vm.initTodoList()
        })
        .catch(e => {
          console.log('error', e)
        })
    }
  }
}
</script>

<style scoped>
.list-group-item-flex {
  display: flex;
  justify-content: space-between;
  align-content: stretch;
  align-items: center;
}

.item-span {
  flex: 1 1 auto;
}
</style>
