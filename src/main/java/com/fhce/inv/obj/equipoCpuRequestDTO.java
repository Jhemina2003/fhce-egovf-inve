package com.fhce.inv.obj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class equipoCpuRequestDTO {
    private equipoRequestDTO equipoRequestDTO;
    private componentePcRequestDTO componentePcRequestDTO;
}