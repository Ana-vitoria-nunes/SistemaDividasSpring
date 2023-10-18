package org.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/verParcela")
public class QuotaViewController {
    @GetMapping("/parcelas")
    public static String viewQuota(){
        return   "Escolha o número de parcelas:"+
        "\n1 - Dividir em 2x"+
        "\n2 - Dividir em 4x"+
        "\n3 - Dividir em 6x"+
        "\n4 - Dividir em 8x"+
        "\n5 - Dividir em 10x"+
        "\n6 - Dividir em 12x";
    }

}
