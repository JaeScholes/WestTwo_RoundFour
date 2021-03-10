var data = {
   
    username: 'username',
    layout: 'list',
    itempage: '1',
    artiles: [{
        title: '',
        url: "https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png",
        showPad: false,
        time: "",
        user:""
    }],
    pages: [{
        title: '',
        url: "https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png",
        showPad: false,
        time: "",
        user: ""
    }]
}
new Vue({
    el: '#container',
    data: data,
    methods: {
        layout_change: function () {
            if (this.layout == 'grid') this.layout = 'list';
            else this.layout = 'grid';
        },

        deleteNewthing: function (index) {
            this.artiles.splice(index, 1);

        },
        lastpage: function () {
            if (this.itempage != 1) this.itempage--;
            else alert("不符合操作规范");
        },
        nextpage: function () {
            if (this.itempage != 6) this.itempage++;
            else alert("已超出页面范围");
        },
        listShow: function () {
            var begin = (this.itempage - 1) * 10;
            var end = this.itempage * 10 - 1;
            this.pages = this.artiles.slice(begin, end);
        }
    }

})