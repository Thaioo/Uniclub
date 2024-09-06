$(document).ready(function(){
    var priceColorSize = []
    var idColor = 0
    var idSize = 0
    var priceSelected = 0
    var searchParam = new URLSearchParams(window.location.search)
    var id = searchParam.get("id")
    
    $.ajax({
        url: `http://localhost:8080/product/detail/${id}`,
        method: "GET",
    }).done(function( item ) {
       
          if(item.statusCode == 200){
            priceColorSize = item.data.priceColorSize

            $('#p-name').html(item.data.name)
            $('#p-price').html(`$${item.data.price}`)
            $('#p-desc').html(item.data.overview)

            var htmlSize = ''
            for(i=0; i<item.data.sizes.length; i++){
                var itemSize = item.data.sizes[i]
                htmlSize += `<li data-value="${itemSize.name}" class="select-item pe-3">
                                <span id-size="${itemSize.id}" class="btn-sizes btn btn-light fs-6">${itemSize.name}</span>
                            </li>`
            }

            $('#p-sizes').append(htmlSize)

            var htmlColors = ''
            for(j=0; j<item.data.colors.length; j++){
                var itemColor = item.data.colors[j]
                htmlColors += `<li class="select-item pe-3" data-val="Gray" title="Gray">
                                <span id-color="${itemColor.id}" class="btn-colors btn btn-light fs-6">${itemColor.name}</span>
                             </li>`
            }

            $('#p-colors').append(htmlColors)
            
          }else{
            
          }
    });

    $('body').on('click','.btn-sizes',function(){
        $('.btn-sizes').removeClass('active')
        $(this).addClass('active')
        idSize = 0
        priceSelected = 0
        idSize = $(this).attr('id-size')
        
        for(i=0; i<priceColorSize.length; i++){
            if(priceColorSize[i].id == idColor){
                for(j=0; j<priceColorSize[i].sizes.length; j++){
                    if(priceColorSize[i].sizes[j].id == idSize){
                        priceSelected = priceColorSize[i].sizes[j].price
                    }
                }
            }
        }

        $('#p-price').html(priceSelected)
    })

    $('body').on('click','.btn-colors',function(){
        $('.btn-colors').removeClass('active')
        $(this).addClass('active')
        idSize = 0
        priceSelected = 0
        idColor = $(this).attr('id-color')

        for(i=0; i<priceColorSize.length; i++){
            if(priceColorSize[i].id == idColor){
                for(j=0; j<priceColorSize[i].sizes.length; j++){
                    if(priceColorSize[i].sizes[j].id == idSize){
                        priceSelected = priceColorSize[i].sizes[j].price
                    }
                }
            }
        }

        $('#p-price').html(priceSelected)
        
    })


})