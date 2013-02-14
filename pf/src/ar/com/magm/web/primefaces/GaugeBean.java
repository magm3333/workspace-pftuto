package ar.com.magm.web.primefaces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.chart.MeterGaugeChartModel;

public class GaugeBean implements Serializable {


	private static final long serialVersionUID = 2450103589061529330L;
	private MeterGaugeChartModel meterGaugeModel;

	public MeterGaugeChartModel getMeterGaugeModel() {
		meterGaugeModel.setValue(Math.random() * 220);
		return meterGaugeModel;
	}

	public GaugeBean() {
		createMeterGaugeModel();
	}

	private void createMeterGaugeModel() {

		List<Number> intervals = new ArrayList<Number>() {
			private static final long serialVersionUID = -1274956022580802653L;

			{
				add(20);
				add(50);
				add(120);
				add(220);
			}
		};

		meterGaugeModel = new MeterGaugeChartModel(0, intervals);
	}
}
