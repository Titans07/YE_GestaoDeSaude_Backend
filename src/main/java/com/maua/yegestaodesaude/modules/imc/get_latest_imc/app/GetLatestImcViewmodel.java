package com.maua.yegestaodesaude.modules.imc.get_latest_imc.app;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetLatestImcViewmodel {
    private Long id;
    private Double weight;
    private Double height;
    private Double imc;
    private String level;
    private String date;
}
