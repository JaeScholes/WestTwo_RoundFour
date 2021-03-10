var data = {
    newthing: '',
    username:'username',
    layout: 'list',
    num:1,
    artiles: [{
        title:"新建文件夹1",
        url: "file:///C:/Users/24427/source/repos/%E5%AF%92%E5%81%87%E8%BD%AE%E9%A1%B9%E7%9B%AE/%E7%94%A8%E6%88%B7%E5%9B%BE%E7%89%87%E6%9F%A5%E7%9C%8B%E9%A1%B5%E9%9D%A2/htmlpage.html",
        showPad: false,

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
        addNewthing: function () {
            if (this.newthing) {
                axios.post('', {

                })
                    .then(function (response) {
                        console.log(response);
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
                this.artiles.push({
                    title: this.newthing,
                    url: 'file:///C:/Users/24427/source/repos/%E5%89%8D%E7%AB%AF%E7%AC%AC%E5%9B%9B%E8%BD%AE/JavaScript.js',
                    showPad: false,
                    cando:false
                }) 
                this.num++;
            }
            else {
                alert("输入不能为空");
            }
            this.newthing=''
        },
        deleteNewthing: function (index) {
            if (this.artiles[index].cando) {
                this.artiles.splice(index, 1);
                this.num--;
            }
            
        },
        
    }
    
})
    