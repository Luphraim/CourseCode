<template>
    <div class="sidebar sidebar-hide-to-small sidebar-shrink sidebar-gestures">
        <div class="nano">
            <div class="nano-content">
                <ul>
                    <br/>
                    <div class="logo">
                        <span style="text-align:center;color:white" v-text="message">Myzone</span>
                         <br/>
                    </div>
                    <li class="label">菜单</li>
                    <li>
                        <a @click="jump('/')">
                            <i class="ti-home"></i> 统计数据
                        </a>
                    </li>
                    <li>
                        <a @click="jump('/alumni')">
                            <i class="ti-layout-grid4-alt"></i> 校友录管理
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</template>

<script>
export default {
  data () {
    return {
      message: 'Myzone',
      isAlumni: false
    }
  },
  watch: {
    $route () {
      this.message = 'Hello ' + this.$store.state.username + '!'
    }
  },
  methods: {
    test () {
      console.log(this.$store.state)
    },
    change (opt) {
      if (opt === 'alumni') {
        this.isAlumni = !this.isAlumni
      }
    },
    jump (curPath) {
      // console.log(curPath)
      if (!(this.$route.path === curPath)) {
        this.$router.push(curPath)
      }
    }
  }
}
</script>

<style scoped>
  @import '../../assets/css/lib/menubar/sidebar.css';
  @import '../../assets/css/lib/font-awesome.min.css';
  @import '../../assets/css/lib/themify-icons.css';
</style>
